package lombok.launch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.util.ResourceUtils;
import org.unbescape.uri.UriEscape;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:lombok/launch/ShadowClassLoader.class */
class ShadowClassLoader extends ClassLoader {
    private static final String SELF_NAME = "lombok/launch/ShadowClassLoader.class";
    private final String SELF_BASE;
    private final File SELF_BASE_FILE;
    private final int SELF_BASE_LENGTH;
    private final List<File> override;
    private final String sclSuffix;
    private final List<String> parentExclusion;
    private final List<String> highlanders;
    private final Set<ClassLoader> prependedParentLoaders;
    private final Map<String, Object> mapJarPathToTracker;
    private Map<String, Boolean> fileRootCache;
    private Map<String, Boolean> jarLocCache;
    private static final ConcurrentMap<String, Class<?>> highlanderMap = new ConcurrentHashMap();
    private static final Map<Object, String> mapTrackerToJarPath = new WeakHashMap();
    private static final Map<Object, Set<String>> mapTrackerToJarContents = new WeakHashMap();

    public void prependParent(ClassLoader loader) {
        if (loader == null || loader == getParent()) {
            return;
        }
        this.prependedParentLoaders.add(loader);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ShadowClassLoader(ClassLoader source, String sclSuffix, String selfBase, List<String> parentExclusion, List<String> highlanders) {
        super(source);
        String[] split;
        this.override = new ArrayList();
        this.parentExclusion = new ArrayList();
        this.highlanders = new ArrayList();
        this.prependedParentLoaders = Collections.newSetFromMap(new IdentityHashMap());
        this.mapJarPathToTracker = new HashMap();
        this.fileRootCache = new HashMap();
        this.jarLocCache = new HashMap();
        this.sclSuffix = sclSuffix;
        if (parentExclusion != null) {
            for (String pe : parentExclusion) {
                String pe2 = pe.replace(".", "/");
                if (!pe2.endsWith("/")) {
                    pe2 = String.valueOf(pe2) + "/";
                }
                this.parentExclusion.add(pe2);
            }
        }
        if (highlanders != null) {
            for (String hl : highlanders) {
                this.highlanders.add(hl);
            }
        }
        if (selfBase != null) {
            this.SELF_BASE = selfBase;
            this.SELF_BASE_LENGTH = selfBase.length();
        } else {
            URL sclClassUrl = ShadowClassLoader.class.getResource("ShadowClassLoader.class");
            String sclClassStr = sclClassUrl == null ? null : sclClassUrl.toString();
            if (sclClassStr == null || !sclClassStr.endsWith(SELF_NAME)) {
                ClassLoader cl = ShadowClassLoader.class.getClassLoader();
                throw new RuntimeException("ShadowLoader can't find itself. SCL loader type: " + (cl == null ? "*NULL*" : cl.getClass().toString()));
            }
            this.SELF_BASE_LENGTH = sclClassStr.length() - SELF_NAME.length();
            String decoded = urlDecode(sclClassStr.substring(0, this.SELF_BASE_LENGTH));
            this.SELF_BASE = decoded;
        }
        if (this.SELF_BASE.startsWith("jar:file:") && this.SELF_BASE.endsWith(ResourceUtils.JAR_URL_SEPARATOR)) {
            this.SELF_BASE_FILE = new File(this.SELF_BASE.substring(9, this.SELF_BASE.length() - 2));
        } else if (this.SELF_BASE.startsWith(ResourceUtils.FILE_URL_PREFIX)) {
            this.SELF_BASE_FILE = new File(this.SELF_BASE.substring(5));
        } else {
            this.SELF_BASE_FILE = new File(this.SELF_BASE);
        }
        String scl = System.getProperty("shadow.override." + sclSuffix);
        if (scl != null && !scl.isEmpty()) {
            for (String part : scl.split("\\s*" + (File.pathSeparatorChar == ';' ? ScriptUtils.DEFAULT_STATEMENT_SEPARATOR : ":") + "\\s*")) {
                if (part.endsWith(ScriptUtils.DEFAULT_BLOCK_COMMENT_START_DELIMITER) || part.endsWith(String.valueOf(File.separator) + "*")) {
                    addOverrideJarDir(part.substring(0, part.length() - 2));
                } else {
                    addOverrideClasspathEntry(part);
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Throwable, java.util.Map<java.lang.Object, java.lang.String>] */
    private Set<String> getOrMakeJarListing(String absolutePathToJar) {
        synchronized (mapTrackerToJarPath) {
            Object ourTracker = this.mapJarPathToTracker.get(absolutePathToJar);
            if (ourTracker != null) {
                return mapTrackerToJarContents.get(ourTracker);
            }
            for (Map.Entry<Object, String> entry : mapTrackerToJarPath.entrySet()) {
                if (entry.getValue().equals(absolutePathToJar)) {
                    Object otherTracker = entry.getKey();
                    this.mapJarPathToTracker.put(absolutePathToJar, otherTracker);
                    return mapTrackerToJarContents.get(otherTracker);
                }
            }
            Object newTracker = new Object();
            Set<String> jarMembers = getJarMemberSet(absolutePathToJar);
            mapTrackerToJarContents.put(newTracker, jarMembers);
            mapTrackerToJarPath.put(newTracker, absolutePathToJar);
            this.mapJarPathToTracker.put(absolutePathToJar, newTracker);
            return jarMembers;
        }
    }

    private Set<String> getJarMemberSet(String absolutePathToJar) {
        try {
            JarFile jar = new JarFile(absolutePathToJar);
            int jarSizePower2 = Integer.highestOneBit(jar.size());
            if (jarSizePower2 != jar.size()) {
                jarSizePower2 <<= 1;
            }
            if (jarSizePower2 == 0) {
                jarSizePower2 = 1;
            }
            Set<String> jarMembers = new HashSet<>(jarSizePower2 >> 1, 1 << 1);
            try {
                Enumeration<JarEntry> entries = jar.entries();
                while (entries.hasMoreElements()) {
                    JarEntry jarEntry = entries.nextElement();
                    if (!jarEntry.isDirectory()) {
                        jarMembers.add(jarEntry.getName());
                    }
                }
                jar.close();
            } catch (Exception unused) {
                jar.close();
            }
            return jarMembers;
        } catch (Exception unused2) {
            return Collections.emptySet();
        }
    }

    private URL getResourceFromLocation(String name, String altName, File location) {
        File absoluteFile;
        if (location.isDirectory()) {
            if (altName != null) {
                try {
                    File f = new File(location, altName);
                    if (f.isFile() && f.canRead()) {
                        return f.toURI().toURL();
                    }
                } catch (MalformedURLException unused) {
                    return null;
                }
            }
            File f2 = new File(location, name);
            if (f2.isFile() && f2.canRead()) {
                return f2.toURI().toURL();
            }
            return null;
        } else if (location.isFile() && location.canRead()) {
            try {
                absoluteFile = location.getCanonicalFile();
            } catch (Exception unused2) {
                absoluteFile = location.getAbsoluteFile();
            }
            Set<String> jarContents = getOrMakeJarListing(absoluteFile.getAbsolutePath());
            String absoluteUri = absoluteFile.toURI().toString();
            try {
                if (jarContents.contains(altName)) {
                    return new URI(ResourceUtils.JAR_URL_PREFIX + absoluteUri + ResourceUtils.JAR_URL_SEPARATOR + altName).toURL();
                }
            } catch (Exception unused3) {
            }
            try {
                if (jarContents.contains(name)) {
                    return new URI(ResourceUtils.JAR_URL_PREFIX + absoluteUri + ResourceUtils.JAR_URL_SEPARATOR + name).toURL();
                }
                return null;
            } catch (Exception unused4) {
                return null;
            }
        } else {
            return null;
        }
    }

    private boolean partOfShadow(String item, String name) {
        if (name.startsWith("java/") || name.startsWith("sun/")) {
            return false;
        }
        return inOwnBase(item, name) || isPartOfShadowSuffix(item, name, this.sclSuffix);
    }

    private boolean inOwnBase(String item, String name) {
        return item != null && item.length() == this.SELF_BASE_LENGTH + name.length() && this.SELF_BASE.regionMatches(0, item, 0, this.SELF_BASE_LENGTH);
    }

    private static boolean sclFileContainsSuffix(InputStream in, String suffix) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in, UriEscape.DEFAULT_ENCODING));
        String readLine = br.readLine();
        while (true) {
            String line = readLine;
            if (line != null) {
                String line2 = line.trim();
                if (!line2.isEmpty() && line2.charAt(0) != '#' && line2.equals(suffix)) {
                    return true;
                }
                readLine = br.readLine();
            } else {
                return false;
            }
        }
    }

    private static String urlDecode(String in) {
        String plusFixed = in.replaceAll("\\+", "%2B");
        try {
            return URLDecoder.decode(plusFixed, UriEscape.DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException unused) {
            throw new InternalError("UTF-8 not supported");
        }
    }

    private boolean isPartOfShadowSuffixFileBased(String fileRoot, String suffix) {
        String key = String.valueOf(fileRoot) + "::" + suffix;
        Boolean existing = this.fileRootCache.get(key);
        if (existing != null) {
            return existing.booleanValue();
        }
        File f = new File(String.valueOf(fileRoot) + "/META-INF/ShadowClassLoader");
        try {
            FileInputStream fis = new FileInputStream(f);
            boolean v = sclFileContainsSuffix(fis, suffix);
            this.fileRootCache.put(key, Boolean.valueOf(v));
            fis.close();
            return v;
        } catch (FileNotFoundException unused) {
            this.fileRootCache.put(key, false);
            return false;
        } catch (IOException unused2) {
            this.fileRootCache.put(key, false);
            return false;
        }
    }

    private boolean isPartOfShadowSuffixJarBased(String jarLoc, String suffix) {
        String key = String.valueOf(jarLoc) + "::" + suffix;
        Boolean existing = this.jarLocCache.get(key);
        if (existing != null) {
            return existing.booleanValue();
        }
        if (jarLoc.startsWith("file:/")) {
            jarLoc = urlDecode(jarLoc.substring(5));
        }
        try {
            FileInputStream jar = new FileInputStream(jarLoc);
            ZipInputStream zip = new ZipInputStream(jar);
            while (true) {
                try {
                    ZipEntry entry = zip.getNextEntry();
                    if (entry == null) {
                        this.jarLocCache.put(key, false);
                        jar.close();
                        return false;
                    } else if ("META-INF/ShadowClassLoader".equals(entry.getName())) {
                        boolean v = sclFileContainsSuffix(zip, suffix);
                        this.jarLocCache.put(key, Boolean.valueOf(v));
                        jar.close();
                        return v;
                    }
                } finally {
                    zip.close();
                }
            }
        } catch (FileNotFoundException unused) {
            this.jarLocCache.put(key, false);
            return false;
        } catch (IOException unused2) {
            this.jarLocCache.put(key, false);
            return false;
        }
    }

    private boolean isPartOfShadowSuffix(String url, String name, String suffix) {
        int sep;
        if (url == null) {
            return false;
        }
        if (url.startsWith("file:/")) {
            String url2 = urlDecode(url.substring(5));
            if (url2.length() <= name.length() || !url2.endsWith(name) || url2.charAt((url2.length() - name.length()) - 1) != '/') {
                return false;
            }
            String fileRoot = url2.substring(0, (url2.length() - name.length()) - 1);
            return isPartOfShadowSuffixFileBased(fileRoot, suffix);
        } else if (!url.startsWith(ResourceUtils.JAR_URL_PREFIX) || (sep = url.indexOf(33)) == -1) {
            return false;
        } else {
            String jarLoc = url.substring(4, sep);
            return isPartOfShadowSuffixJarBased(jarLoc, suffix);
        }
    }

    @Override // java.lang.ClassLoader
    public Enumeration<URL> getResources(String name) throws IOException {
        URL fromSelf;
        String altName = name.endsWith(".class") ? String.valueOf(name.substring(0, name.length() - 6)) + ".SCL." + this.sclSuffix : null;
        Vector<URL> vector = new Vector<>();
        for (File ce : this.override) {
            URL url = getResourceFromLocation(name, altName, ce);
            if (url != null) {
                vector.add(url);
            }
        }
        if (this.override.isEmpty() && (fromSelf = getResourceFromLocation(name, altName, this.SELF_BASE_FILE)) != null) {
            vector.add(fromSelf);
        }
        Enumeration<URL> sec = super.getResources(name);
        while (sec.hasMoreElements()) {
            URL item = sec.nextElement();
            if (isPartOfShadowSuffix(item.toString(), name, this.sclSuffix)) {
                vector.add(item);
            }
        }
        if (altName != null) {
            Enumeration<URL> tern = super.getResources(altName);
            while (tern.hasMoreElements()) {
                URL item2 = tern.nextElement();
                if (isPartOfShadowSuffix(item2.toString(), altName, this.sclSuffix)) {
                    vector.add(item2);
                }
            }
        }
        return vector.elements();
    }

    @Override // java.lang.ClassLoader
    public URL getResource(String name) {
        return getResource_(name, false);
    }

    private URL getResource_(String name, boolean noSuper) {
        URL res;
        String altName = name.endsWith(".class") ? String.valueOf(name.substring(0, name.length() - 6)) + ".SCL." + this.sclSuffix : null;
        for (File ce : this.override) {
            URL url = getResourceFromLocation(name, altName, ce);
            if (url != null) {
                return url;
            }
        }
        if (!this.override.isEmpty()) {
            if (noSuper) {
                return null;
            }
            if (altName != null) {
                try {
                    URL res2 = getResourceSkippingSelf(altName);
                    if (res2 != null) {
                        return res2;
                    }
                } catch (IOException unused) {
                }
            }
            try {
                return getResourceSkippingSelf(name);
            } catch (IOException unused2) {
                return null;
            }
        }
        URL url2 = getResourceFromLocation(name, altName, this.SELF_BASE_FILE);
        if (url2 != null) {
            return url2;
        }
        if (altName == null || (res = super.getResource(altName)) == null || (noSuper && !partOfShadow(res.toString(), altName))) {
            URL res3 = super.getResource(name);
            if (res3 != null) {
                if (!noSuper || partOfShadow(res3.toString(), name)) {
                    return res3;
                }
                return null;
            }
            return null;
        }
        return res;
    }

    private boolean exclusionListMatch(String name) {
        for (String pe : this.parentExclusion) {
            if (name.startsWith(pe)) {
                return true;
            }
        }
        return false;
    }

    private URL getResourceSkippingSelf(String name) throws IOException {
        URL candidate = super.getResource(name);
        if (candidate == null) {
            return null;
        }
        if (partOfShadow(candidate.toString(), name)) {
            Enumeration<URL> en = super.getResources(name);
            while (en.hasMoreElements()) {
                URL candidate2 = en.nextElement();
                if (!partOfShadow(candidate2.toString(), name)) {
                    return candidate2;
                }
            }
            return null;
        }
        return candidate;
    }

    @Override // java.lang.ClassLoader
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> loadClass;
        Class<?> c;
        Class<?> alreadyLoaded = findLoadedClass(name);
        if (alreadyLoaded != null) {
            return alreadyLoaded;
        }
        if (!this.highlanders.contains(name) || (c = highlanderMap.get(name)) == null) {
            String fileNameOfClass = String.valueOf(name.replace(".", "/")) + ".class";
            URL res = getResource_(fileNameOfClass, true);
            if (res == null && !exclusionListMatch(fileNameOfClass)) {
                try {
                    for (ClassLoader pre : this.prependedParentLoaders) {
                        try {
                            loadClass = pre.loadClass(name);
                        } catch (Throwable unused) {
                        }
                        if (loadClass != null) {
                            return loadClass;
                        }
                    }
                    return super.loadClass(name, resolve);
                } catch (ClassNotFoundException cnfe) {
                    res = getResource_("secondaryLoading.SCL." + this.sclSuffix + "/" + name.replace(".", "/") + ".SCL." + this.sclSuffix, true);
                    if (res == null) {
                        throw cnfe;
                    }
                }
            }
            if (res == null) {
                throw new ClassNotFoundException(name);
            }
            return urlToDefineClass(name, res, resolve);
        }
        return c;
    }

    private Class<?> urlToDefineClass(String name, URL res, boolean resolve) throws ClassNotFoundException {
        Class<?> c;
        Class<?> alreadyDefined;
        Class<?> alreadyDefined2;
        int p = 0;
        try {
            InputStream in = res.openStream();
            byte[] b = new byte[65536];
            while (true) {
                int r = in.read(b, p, b.length - p);
                if (r == -1) {
                    break;
                }
                p += r;
                if (p == b.length) {
                    byte[] nb = new byte[b.length * 2];
                    System.arraycopy(b, 0, nb, 0, p);
                    b = nb;
                }
            }
            in.close();
            try {
                c = defineClass(name, b, 0, p);
            } catch (LinkageError e) {
                if (this.highlanders.contains(name) && (alreadyDefined = highlanderMap.get(name)) != null) {
                    return alreadyDefined;
                }
                try {
                    c = findLoadedClass(name);
                    if (c == null) {
                        throw e;
                    }
                } catch (LinkageError unused) {
                    throw e;
                }
            }
            if (this.highlanders.contains(name) && (alreadyDefined2 = highlanderMap.putIfAbsent(name, c)) != null) {
                c = alreadyDefined2;
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        } catch (IOException e2) {
            throw new ClassNotFoundException("I/O exception reading class " + name, e2);
        }
    }

    public void addOverrideJarDir(String dir) {
        File[] listFiles;
        File f = new File(dir);
        for (File j : f.listFiles()) {
            if (j.getName().toLowerCase().endsWith(".jar") && j.canRead() && j.isFile()) {
                this.override.add(j);
            }
        }
    }

    public void addOverrideClasspathEntry(String entry) {
        this.override.add(new File(entry));
    }
}