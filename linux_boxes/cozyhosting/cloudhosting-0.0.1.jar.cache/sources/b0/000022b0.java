package org.aspectj.apache.bcel.util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.bytebuddy.ClassFileVersion;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/apache/bcel/util/ClassPath.class */
public class ClassPath implements Serializable {
    private static final String JRT_FS = "jrt-fs.jar";
    private static ClassPath SYSTEM_CLASS_PATH = null;
    private PathEntry[] paths;
    private String class_path;

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/apache/bcel/util/ClassPath$ClassFile.class */
    public interface ClassFile {
        InputStream getInputStream() throws IOException;

        String getPath();

        String getBase();

        long getTime();

        long getSize();
    }

    public static ClassPath getSystemClassPath() {
        if (SYSTEM_CLASS_PATH == null) {
            SYSTEM_CLASS_PATH = new ClassPath();
        }
        return SYSTEM_CLASS_PATH;
    }

    public ClassPath(String class_path) {
        this.class_path = class_path;
        List<PathEntry> vec = new ArrayList<>();
        StringTokenizer tok = new StringTokenizer(class_path, System.getProperty("path.separator"));
        while (tok.hasMoreTokens()) {
            String path = tok.nextToken();
            if (!path.equals("")) {
                File file = new File(path);
                try {
                    if (file.isDirectory()) {
                        vec.add(new Dir(path));
                    } else if (file.exists()) {
                        if (file.getName().endsWith("jrt-fs.jar")) {
                            vec.add(new JImage());
                        } else {
                            vec.add(new Zip(new ZipFile(file)));
                        }
                    }
                } catch (IOException e) {
                    System.err.println("CLASSPATH component " + file + ": " + e);
                }
            }
        }
        this.paths = new PathEntry[vec.size()];
        vec.toArray(this.paths);
    }

    @Deprecated
    public ClassPath() {
        this(getClassPath());
    }

    public String toString() {
        return this.class_path;
    }

    public int hashCode() {
        return this.class_path.hashCode();
    }

    public boolean equals(Object o) {
        if (o instanceof ClassPath) {
            return this.class_path.equals(((ClassPath) o).class_path);
        }
        return false;
    }

    private static final void getPathComponents(String path, List<String> list) {
        if (path != null) {
            StringTokenizer tok = new StringTokenizer(path, File.pathSeparator);
            while (tok.hasMoreTokens()) {
                String name = tok.nextToken();
                File file = new File(name);
                if (file.exists()) {
                    list.add(name);
                }
            }
        }
    }

    public static final String getClassPath() {
        String class_path = System.getProperty("java.class.path");
        String boot_path = System.getProperty("sun.boot.class.path");
        String ext_path = System.getProperty("java.ext.dirs");
        String vm_version = System.getProperty(ClassFileVersion.VersionLocator.JAVA_VERSION);
        ArrayList<String> list = new ArrayList<>();
        getPathComponents(class_path, list);
        getPathComponents(boot_path, list);
        ArrayList<String> dirs = new ArrayList<>();
        getPathComponents(ext_path, dirs);
        Iterator<String> it = dirs.iterator();
        while (it.hasNext()) {
            String string = it.next();
            File ext_dir = new File(string);
            String[] extensions = ext_dir.list(new FilenameFilter() { // from class: org.aspectj.apache.bcel.util.ClassPath.1
                @Override // java.io.FilenameFilter
                public boolean accept(File dir, String name) {
                    String name2 = name.toLowerCase();
                    return name2.endsWith(".zip") || name2.endsWith(".jar");
                }
            });
            if (extensions != null) {
                for (String extension : extensions) {
                    list.add(ext_dir.toString() + File.separatorChar + extension);
                }
            }
        }
        StringBuilder buf = new StringBuilder();
        Iterator<String> e = list.iterator();
        while (e.hasNext()) {
            buf.append(e.next());
            if (e.hasNext()) {
                buf.append(File.pathSeparatorChar);
            }
        }
        if (vm_version.matches("^(9|10|11|12|13|14|15|16|17|18|19).*")) {
            buf.insert(0, File.pathSeparatorChar);
            buf.insert(0, System.getProperty("java.home") + File.separator + "lib" + File.separator + "jrt-fs.jar");
        }
        return buf.toString().intern();
    }

    public InputStream getInputStream(String name) throws IOException {
        return getInputStream(name, ".class");
    }

    public InputStream getInputStream(String name, String suffix) throws IOException {
        InputStream is = null;
        try {
            is = getClass().getClassLoader().getResourceAsStream(name + suffix);
        } catch (Exception e) {
        }
        if (is != null) {
            return is;
        }
        return getClassFile(name, suffix).getInputStream();
    }

    public ClassFile getClassFile(String name, String suffix) throws IOException {
        PathEntry[] pathEntryArr;
        for (PathEntry path : this.paths) {
            ClassFile cf = path.getClassFile(name, suffix);
            if (cf != null) {
                return cf;
            }
        }
        throw new IOException("Couldn't find: " + name + suffix);
    }

    public ClassFile getClassFile(String name) throws IOException {
        return getClassFile(name, ".class");
    }

    public byte[] getBytes(String name, String suffix) throws IOException {
        InputStream is = getInputStream(name, suffix);
        if (is == null) {
            throw new IOException("Couldn't find: " + name + suffix);
        }
        DataInputStream dis = new DataInputStream(is);
        byte[] bytes = new byte[is.available()];
        dis.readFully(bytes);
        dis.close();
        is.close();
        return bytes;
    }

    public byte[] getBytes(String name) throws IOException {
        return getBytes(name, ".class");
    }

    public String getPath(String name) throws IOException {
        int index = name.lastIndexOf(46);
        String suffix = "";
        if (index > 0) {
            suffix = name.substring(index);
            name = name.substring(0, index);
        }
        return getPath(name, suffix);
    }

    public String getPath(String name, String suffix) throws IOException {
        return getClassFile(name, suffix).getPath();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/apache/bcel/util/ClassPath$PathEntry.class */
    public static abstract class PathEntry implements Serializable {
        abstract ClassFile getClassFile(String str, String str2) throws IOException;

        private PathEntry() {
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/apache/bcel/util/ClassPath$Dir.class */
    private static class Dir extends PathEntry {
        private String dir;

        Dir(String d) {
            super();
            this.dir = d;
        }

        @Override // org.aspectj.apache.bcel.util.ClassPath.PathEntry
        ClassFile getClassFile(String name, String suffix) throws IOException {
            final File file = new File(this.dir + File.separatorChar + name.replace('.', File.separatorChar) + suffix);
            if (file.exists()) {
                return new ClassFile() { // from class: org.aspectj.apache.bcel.util.ClassPath.Dir.1
                    @Override // org.aspectj.apache.bcel.util.ClassPath.ClassFile
                    public InputStream getInputStream() throws IOException {
                        return new FileInputStream(file);
                    }

                    @Override // org.aspectj.apache.bcel.util.ClassPath.ClassFile
                    public String getPath() {
                        try {
                            return file.getCanonicalPath();
                        } catch (IOException e) {
                            return null;
                        }
                    }

                    @Override // org.aspectj.apache.bcel.util.ClassPath.ClassFile
                    public long getTime() {
                        return file.lastModified();
                    }

                    @Override // org.aspectj.apache.bcel.util.ClassPath.ClassFile
                    public long getSize() {
                        return file.length();
                    }

                    @Override // org.aspectj.apache.bcel.util.ClassPath.ClassFile
                    public String getBase() {
                        return Dir.this.dir;
                    }
                };
            }
            return null;
        }

        public String toString() {
            return this.dir;
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/apache/bcel/util/ClassPath$JImage.class */
    private static class JImage extends PathEntry {
        private static URI JRT_URI = URI.create("jrt:/");
        private static String MODULES_PATH = "modules";
        private static String JAVA_BASE_PATH = "java.base";
        private FileSystem fs;
        private final Map<String, Path> fileMap;

        JImage() {
            super();
            this.fs = FileSystems.getFileSystem(JRT_URI);
            this.fileMap = buildFileMap();
        }

        private Map<String, Path> buildFileMap() {
            final Map<String, Path> fileMap = new HashMap<>();
            final PathMatcher matcher = this.fs.getPathMatcher("glob:*.class");
            Iterable<Path> roots = this.fs.getRootDirectories();
            for (Path path : roots) {
                try {
                    Files.walkFileTree(path, new SimpleFileVisitor<Path>() { // from class: org.aspectj.apache.bcel.util.ClassPath.JImage.1
                        @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            if (file.getNameCount() > 2 && matcher.matches(file.getFileName())) {
                                Path classPath = file.subpath(2, file.getNameCount());
                                fileMap.put(classPath.toString(), file);
                            }
                            return FileVisitResult.CONTINUE;
                        }
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return fileMap;
        }

        /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/apache/bcel/util/ClassPath$JImage$ByteBasedClassFile.class */
        private static class ByteBasedClassFile implements ClassFile {
            private byte[] bytes;
            private ByteArrayInputStream bais;
            private String path;
            private String base;
            private long time;
            private long size;

            public ByteBasedClassFile(byte[] bytes, String path, String base, long time, long size) {
                this.bytes = bytes;
                this.path = path;
                this.base = base;
                this.time = time;
                this.size = size;
            }

            @Override // org.aspectj.apache.bcel.util.ClassPath.ClassFile
            public InputStream getInputStream() throws IOException {
                this.bais = new ByteArrayInputStream(this.bytes);
                return this.bais;
            }

            @Override // org.aspectj.apache.bcel.util.ClassPath.ClassFile
            public String getPath() {
                return this.path;
            }

            @Override // org.aspectj.apache.bcel.util.ClassPath.ClassFile
            public String getBase() {
                return this.base;
            }

            @Override // org.aspectj.apache.bcel.util.ClassPath.ClassFile
            public long getTime() {
                return this.time;
            }

            @Override // org.aspectj.apache.bcel.util.ClassPath.ClassFile
            public long getSize() {
                return this.size;
            }
        }

        @Override // org.aspectj.apache.bcel.util.ClassPath.PathEntry
        ClassFile getClassFile(String name, String suffix) throws IOException {
            String fileName = name.replace('.', '/') + suffix;
            Path p = this.fileMap.get(fileName);
            if (p == null) {
                return null;
            }
            byte[] bs = Files.readAllBytes(p);
            BasicFileAttributeView bfav = (BasicFileAttributeView) Files.getFileAttributeView(p, BasicFileAttributeView.class, new LinkOption[0]);
            BasicFileAttributes bfas = bfav.readAttributes();
            long time = bfas.lastModifiedTime().toMillis();
            long size = bfas.size();
            ClassFile cf = new ByteBasedClassFile(bs, "jimage", fileName, time, size);
            return cf;
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/apache/bcel/util/ClassPath$Zip.class */
    private static class Zip extends PathEntry {
        private ZipFile zip;

        Zip(ZipFile z) {
            super();
            this.zip = z;
        }

        @Override // org.aspectj.apache.bcel.util.ClassPath.PathEntry
        ClassFile getClassFile(String name, String suffix) throws IOException {
            final ZipEntry entry = this.zip.getEntry(name.replace('.', '/') + suffix);
            if (entry != null) {
                return new ClassFile() { // from class: org.aspectj.apache.bcel.util.ClassPath.Zip.1
                    @Override // org.aspectj.apache.bcel.util.ClassPath.ClassFile
                    public InputStream getInputStream() throws IOException {
                        return Zip.this.zip.getInputStream(entry);
                    }

                    @Override // org.aspectj.apache.bcel.util.ClassPath.ClassFile
                    public String getPath() {
                        return entry.toString();
                    }

                    @Override // org.aspectj.apache.bcel.util.ClassPath.ClassFile
                    public long getTime() {
                        return entry.getTime();
                    }

                    @Override // org.aspectj.apache.bcel.util.ClassPath.ClassFile
                    public long getSize() {
                        return entry.getSize();
                    }

                    @Override // org.aspectj.apache.bcel.util.ClassPath.ClassFile
                    public String getBase() {
                        return Zip.this.zip.getName();
                    }
                };
            }
            return null;
        }
    }
}