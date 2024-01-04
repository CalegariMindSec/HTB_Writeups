package lombok.core.configuration;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.unbescape.uri.UriEscape;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:lombok/core/configuration/ConfigurationFile.SCL.lombok */
public abstract class ConfigurationFile {
    private static final String LOMBOK_CONFIG_FILENAME = "lombok.config";
    private final String identifier;
    private static final Pattern VARIABLE = Pattern.compile("\\<(.+?)\\>");
    private static final Map<String, String> ENV = new HashMap(System.getenv());
    private static final ThreadLocal<byte[]> buffers = new ThreadLocal<byte[]>() { // from class: lombok.core.configuration.ConfigurationFile.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public byte[] initialValue() {
            return new byte[65536];
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract long getLastModifiedOrMissing();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean exists();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract CharSequence contents() throws IOException;

    public abstract ConfigurationFile resolve(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract ConfigurationFile parent();

    static void setEnvironment(String key, String value) {
        ENV.put(key, value);
    }

    public static ConfigurationFile forFile(File file) {
        return new RegularConfigurationFile(file, null);
    }

    public static ConfigurationFile forDirectory(File directory) {
        return forFile(new File(directory, LOMBOK_CONFIG_FILENAME));
    }

    public static ConfigurationFile fromCharSequence(String identifier, CharSequence contents, long lastModified) {
        return new CharSequenceConfigurationFile(identifier, contents, lastModified, null);
    }

    private ConfigurationFile(String identifier) {
        this.identifier = identifier;
    }

    /* synthetic */ ConfigurationFile(String str, ConfigurationFile configurationFile) {
        this(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String description() {
        return this.identifier;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof ConfigurationFile) {
            return this.identifier.equals(((ConfigurationFile) obj).identifier);
        }
        return false;
    }

    public final int hashCode() {
        return this.identifier.hashCode();
    }

    public static long getLastModifiedOrMissing(File file) {
        if (fileExists(file)) {
            return file.lastModified();
        }
        return -88L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean fileExists(File file) {
        return file.exists() && file.isFile();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String read(InputStream is) throws IOException {
        byte[] b = buffers.get();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        while (true) {
            int r = is.read(b);
            if (r != -1) {
                out.write(b, 0, r);
            } else {
                return new String(out.toByteArray(), UriEscape.DEFAULT_ENCODING);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:lombok/core/configuration/ConfigurationFile$RegularConfigurationFile.SCL.lombok */
    public static class RegularConfigurationFile extends ConfigurationFile {
        private final File file;

        /* synthetic */ RegularConfigurationFile(File file, RegularConfigurationFile regularConfigurationFile) {
            this(file);
        }

        private RegularConfigurationFile(File file) {
            super(file.getPath(), null);
            this.file = file;
        }

        @Override // lombok.core.configuration.ConfigurationFile
        boolean exists() {
            return ConfigurationFile.fileExists(this.file);
        }

        @Override // lombok.core.configuration.ConfigurationFile
        public ConfigurationFile resolve(String path) {
            if (path.endsWith("!")) {
                return null;
            }
            String[] parts = path.split("!");
            if (parts.length > 2) {
                return null;
            }
            String realFileName = parts[0];
            File file = resolveFile(replaceEnvironmentVariables(realFileName));
            if (realFileName.endsWith(".zip") || realFileName.endsWith(".jar")) {
                try {
                    return ArchivedConfigurationFile.create(file, URI.create(parts.length == 1 ? ConfigurationFile.LOMBOK_CONFIG_FILENAME : parts[1]));
                } catch (Exception unused) {
                    return null;
                }
            } else if (parts.length <= 1 && file != null) {
                return forFile(file);
            } else {
                return null;
            }
        }

        private File resolveFile(String path) {
            char firstCharacter;
            boolean absolute = false;
            int colon = path.indexOf(58);
            if (colon != -1) {
                if (colon != 1 || path.indexOf(58, colon + 1) != -1 || (firstCharacter = Character.toLowerCase(path.charAt(0))) < 'a' || firstCharacter > 'z') {
                    return null;
                }
                absolute = true;
            }
            if (path.charAt(0) == '/') {
                absolute = true;
            }
            try {
                return absolute ? new File(path) : new File(this.file.toURI().resolve(path));
            } catch (Exception unused) {
                return null;
            }
        }

        @Override // lombok.core.configuration.ConfigurationFile
        long getLastModifiedOrMissing() {
            return getLastModifiedOrMissing(this.file);
        }

        @Override // lombok.core.configuration.ConfigurationFile
        CharSequence contents() throws IOException {
            FileInputStream is = new FileInputStream(this.file);
            try {
                return ConfigurationFile.read(is);
            } finally {
                is.close();
            }
        }

        @Override // lombok.core.configuration.ConfigurationFile
        ConfigurationFile parent() {
            File parent = this.file.getParentFile().getParentFile();
            if (parent == null) {
                return null;
            }
            return forDirectory(parent);
        }

        private static String replaceEnvironmentVariables(String fileName) {
            int start = 0;
            StringBuffer result = new StringBuffer();
            if (fileName.startsWith("~")) {
                start = 1;
                result.append(System.getProperty("user.home", "~"));
            }
            Matcher matcher = ConfigurationFile.VARIABLE.matcher(fileName.substring(start));
            while (matcher.find()) {
                String key = matcher.group(1);
                String value = (String) ConfigurationFile.ENV.get(key);
                if (value == null) {
                    value = "<" + key + ">";
                }
                matcher.appendReplacement(result, value);
            }
            matcher.appendTail(result);
            return result.toString();
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:lombok/core/configuration/ConfigurationFile$ArchivedConfigurationFile.SCL.lombok */
    private static class ArchivedConfigurationFile extends ConfigurationFile {
        private static final URI ROOT1 = URI.create("http://x.y/a/");
        private static final URI ROOT2 = URI.create("ftp://y.x/b/");
        private static final ConcurrentMap<String, Object> locks = new ConcurrentHashMap();
        private final File archive;
        private final URI file;
        private final Object lock;
        private long lastModified;
        private String contents;

        public static ConfigurationFile create(File archive, URI file) {
            if (isRelative(file)) {
                return new ArchivedConfigurationFile(archive, file, String.valueOf(archive.getPath()) + "!" + file.getPath());
            }
            return null;
        }

        static boolean isRelative(URI path) {
            try {
                if (ROOT1.resolve(path).toString().startsWith(ROOT1.toString())) {
                    return ROOT2.resolve(path).toString().startsWith(ROOT2.toString());
                }
                return false;
            } catch (Exception unused) {
                return false;
            }
        }

        ArchivedConfigurationFile(File archive, URI file, String description) {
            super(description, null);
            this.lastModified = -2L;
            this.archive = archive;
            this.file = file;
            locks.putIfAbsent(archive.getPath(), new Object());
            this.lock = locks.get(archive.getPath());
        }

        @Override // lombok.core.configuration.ConfigurationFile
        long getLastModifiedOrMissing() {
            return getLastModifiedOrMissing(this.archive);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
        @Override // lombok.core.configuration.ConfigurationFile
        boolean exists() {
            boolean z;
            if (ConfigurationFile.fileExists(this.archive)) {
                ?? r0 = this.lock;
                synchronized (r0) {
                    try {
                        readIfNeccesary();
                        r0 = this.contents;
                        z = r0 != 0;
                    } catch (Exception unused) {
                        return false;
                    }
                }
                return z;
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.CharSequence, java.lang.String] */
        @Override // lombok.core.configuration.ConfigurationFile
        CharSequence contents() throws IOException {
            ?? r0 = this.lock;
            synchronized (r0) {
                readIfNeccesary();
                r0 = this.contents;
            }
            return r0;
        }

        void readIfNeccesary() throws IOException {
            long archiveModified = getLastModifiedOrMissing();
            if (archiveModified == this.lastModified) {
                return;
            }
            this.contents = null;
            this.lastModified = archiveModified;
            if (archiveModified == -88) {
                return;
            }
            this.contents = read();
        }

        private String read() throws IOException {
            ZipEntry entry;
            FileInputStream is = new FileInputStream(this.archive);
            try {
                ZipInputStream zip = new ZipInputStream(is);
                do {
                    entry = zip.getNextEntry();
                    if (entry == null) {
                        zip.close();
                        is.close();
                        return null;
                    }
                } while (!entry.getName().equals(this.file.getPath()));
                String read = ConfigurationFile.read(zip);
                zip.close();
                return read;
            } finally {
                is.close();
            }
        }

        @Override // lombok.core.configuration.ConfigurationFile
        public ConfigurationFile resolve(String path) {
            try {
                URI resolved = this.file.resolve(path);
                if (isRelative(resolved)) {
                    return create(this.archive, resolved);
                }
                return null;
            } catch (Exception unused) {
                return null;
            }
        }

        @Override // lombok.core.configuration.ConfigurationFile
        ConfigurationFile parent() {
            return null;
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:lombok/core/configuration/ConfigurationFile$CharSequenceConfigurationFile.SCL.lombok */
    private static class CharSequenceConfigurationFile extends ConfigurationFile {
        private final CharSequence contents;
        private final long lastModified;

        /* synthetic */ CharSequenceConfigurationFile(String str, CharSequence charSequence, long j, CharSequenceConfigurationFile charSequenceConfigurationFile) {
            this(str, charSequence, j);
        }

        private CharSequenceConfigurationFile(String identifier, CharSequence contents, long lastModified) {
            super(identifier, null);
            this.contents = contents;
            this.lastModified = lastModified;
        }

        @Override // lombok.core.configuration.ConfigurationFile
        long getLastModifiedOrMissing() {
            return this.lastModified;
        }

        @Override // lombok.core.configuration.ConfigurationFile
        CharSequence contents() throws IOException {
            return this.contents;
        }

        @Override // lombok.core.configuration.ConfigurationFile
        boolean exists() {
            return true;
        }

        @Override // lombok.core.configuration.ConfigurationFile
        public ConfigurationFile resolve(String path) {
            return null;
        }

        @Override // lombok.core.configuration.ConfigurationFile
        ConfigurationFile parent() {
            return null;
        }
    }
}