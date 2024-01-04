package net.bytebuddy.dynamic.loading;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.Attributes;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.utility.nullability.AlwaysNull;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy.class */
public interface PackageDefinitionStrategy {
    Definition define(ClassLoader classLoader, String str, String str2);

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy$NoOp.class */
    public enum NoOp implements PackageDefinitionStrategy {
        INSTANCE;

        @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy
        public Definition define(ClassLoader classLoader, String packageName, String typeName) {
            return Definition.Undefined.INSTANCE;
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy$Trivial.class */
    public enum Trivial implements PackageDefinitionStrategy {
        INSTANCE;

        @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy
        public Definition define(ClassLoader classLoader, String packageName, String typeName) {
            return Definition.Trivial.INSTANCE;
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy$Definition.class */
    public interface Definition {
        boolean isDefined();

        @MaybeNull
        String getSpecificationTitle();

        @MaybeNull
        String getSpecificationVersion();

        @MaybeNull
        String getSpecificationVendor();

        @MaybeNull
        String getImplementationTitle();

        @MaybeNull
        String getImplementationVersion();

        @MaybeNull
        String getImplementationVendor();

        @MaybeNull
        URL getSealBase();

        boolean isCompatibleTo(Package r1);

        /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy$Definition$Undefined.class */
        public enum Undefined implements Definition {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public boolean isDefined() {
                return false;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public String getSpecificationTitle() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public String getSpecificationVersion() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public String getSpecificationVendor() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public String getImplementationTitle() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public String getImplementationVersion() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public String getImplementationVendor() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public URL getSealBase() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public boolean isCompatibleTo(Package definedPackage) {
                throw new IllegalStateException("Cannot check compatibility to undefined package");
            }
        }

        /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy$Definition$Trivial.class */
        public enum Trivial implements Definition {
            INSTANCE;
            
            @AlwaysNull
            private static final String NO_VALUE = null;
            @AlwaysNull
            private static final URL NOT_SEALED = null;

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public boolean isDefined() {
                return true;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public String getSpecificationTitle() {
                return NO_VALUE;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public String getSpecificationVersion() {
                return NO_VALUE;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public String getSpecificationVendor() {
                return NO_VALUE;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public String getImplementationTitle() {
                return NO_VALUE;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public String getImplementationVersion() {
                return NO_VALUE;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public String getImplementationVendor() {
                return NO_VALUE;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public URL getSealBase() {
                return NOT_SEALED;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public boolean isCompatibleTo(Package definedPackage) {
                return true;
            }
        }

        /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy$Definition$Simple.class */
        public static class Simple implements Definition {
            @MaybeNull
            protected final URL sealBase;
            @MaybeNull
            private final String specificationTitle;
            @MaybeNull
            private final String specificationVersion;
            @MaybeNull
            private final String specificationVendor;
            @MaybeNull
            private final String implementationTitle;
            @MaybeNull
            private final String implementationVersion;
            @MaybeNull
            private final String implementationVendor;

            public Simple(@MaybeNull String specificationTitle, @MaybeNull String specificationVersion, @MaybeNull String specificationVendor, @MaybeNull String implementationTitle, @MaybeNull String implementationVersion, @MaybeNull String implementationVendor, @MaybeNull URL sealBase) {
                this.specificationTitle = specificationTitle;
                this.specificationVersion = specificationVersion;
                this.specificationVendor = specificationVendor;
                this.implementationTitle = implementationTitle;
                this.implementationVersion = implementationVersion;
                this.implementationVendor = implementationVendor;
                this.sealBase = sealBase;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public boolean isDefined() {
                return true;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public String getSpecificationTitle() {
                return this.specificationTitle;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public String getSpecificationVersion() {
                return this.specificationVersion;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public String getSpecificationVendor() {
                return this.specificationVendor;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public String getImplementationTitle() {
                return this.implementationTitle;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public String getImplementationVersion() {
                return this.implementationVersion;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public String getImplementationVendor() {
                return this.implementationVendor;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public URL getSealBase() {
                return this.sealBase;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public boolean isCompatibleTo(Package definedPackage) {
                if (this.sealBase == null) {
                    return !definedPackage.isSealed();
                }
                return definedPackage.isSealed(this.sealBase);
            }

            @SuppressFBWarnings(value = {"DMI_BLOCKING_METHODS_ON_URL"}, justification = "Package sealing relies on URL equality.")
            public int hashCode() {
                int result = this.specificationTitle != null ? this.specificationTitle.hashCode() : 0;
                return (31 * ((31 * ((31 * ((31 * ((31 * ((31 * result) + (this.specificationVersion != null ? this.specificationVersion.hashCode() : 0))) + (this.specificationVendor != null ? this.specificationVendor.hashCode() : 0))) + (this.implementationTitle != null ? this.implementationTitle.hashCode() : 0))) + (this.implementationVersion != null ? this.implementationVersion.hashCode() : 0))) + (this.implementationVendor != null ? this.implementationVendor.hashCode() : 0))) + (this.sealBase != null ? this.sealBase.hashCode() : 0);
            }

            @SuppressFBWarnings(value = {"DMI_BLOCKING_METHODS_ON_URL"}, justification = "Package sealing relies on URL equality.")
            public boolean equals(@MaybeNull Object other) {
                if (this == other) {
                    return true;
                }
                if (other == null || getClass() != other.getClass()) {
                    return false;
                }
                Simple simple = (Simple) other;
                if (this.specificationTitle == null ? simple.specificationTitle == null : this.specificationTitle.equals(simple.specificationTitle)) {
                    if (this.specificationVersion == null ? simple.specificationVersion == null : this.specificationVersion.equals(simple.specificationVersion)) {
                        if (this.specificationVendor == null ? simple.specificationVendor == null : this.specificationVendor.equals(simple.specificationVendor)) {
                            if (this.implementationTitle == null ? simple.implementationTitle == null : this.implementationTitle.equals(simple.implementationTitle)) {
                                if (this.implementationVersion == null ? simple.implementationVersion == null : this.implementationVersion.equals(simple.implementationVersion)) {
                                    if (this.implementationVendor == null ? simple.implementationVendor == null : this.implementationVendor.equals(simple.implementationVendor)) {
                                        if (this.sealBase == null ? simple.sealBase == null : this.sealBase.equals(simple.sealBase)) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return false;
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy$ManifestReading.class */
    public static class ManifestReading implements PackageDefinitionStrategy {
        @AlwaysNull
        private static final URL NOT_SEALED = null;
        private static final Attributes.Name[] ATTRIBUTE_NAMES = {Attributes.Name.SPECIFICATION_TITLE, Attributes.Name.SPECIFICATION_VERSION, Attributes.Name.SPECIFICATION_VENDOR, Attributes.Name.IMPLEMENTATION_TITLE, Attributes.Name.IMPLEMENTATION_VERSION, Attributes.Name.IMPLEMENTATION_VENDOR, Attributes.Name.SEALED};
        private final SealBaseLocator sealBaseLocator;

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: JavaClassParseException: Unknown opcode: 0xa8 in method: net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.ManifestReading.define(java.lang.ClassLoader, java.lang.String, java.lang.String):net.bytebuddy.dynamic.loading.PackageDefinitionStrategy$Definition, file: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy$ManifestReading.class
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:307)
            Caused by: jadx.plugins.input.java.utils.JavaClassParseException: Unknown opcode: 0xa8
            	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:71)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	... 6 more
            */
        @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy
        public net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition define(java.lang.ClassLoader r1, java.lang.String r2, java.lang.String r3) {
            /*
            // Can't load method instructions: Load method exception: JavaClassParseException: Unknown opcode: 0xa8 in method: net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.ManifestReading.define(java.lang.ClassLoader, java.lang.String, java.lang.String):net.bytebuddy.dynamic.loading.PackageDefinitionStrategy$Definition, file: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy$ManifestReading.class
            */
            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.ManifestReading.define(java.lang.ClassLoader, java.lang.String, java.lang.String):net.bytebuddy.dynamic.loading.PackageDefinitionStrategy$Definition");
        }

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.sealBaseLocator.equals(((ManifestReading) obj).sealBaseLocator);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.sealBaseLocator.hashCode();
        }

        public ManifestReading() {
            this(new SealBaseLocator.ForTypeResourceUrl());
        }

        public ManifestReading(SealBaseLocator sealBaseLocator) {
            this.sealBaseLocator = sealBaseLocator;
        }

        /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy$ManifestReading$SealBaseLocator.class */
        public interface SealBaseLocator {
            @MaybeNull
            URL findSealBase(ClassLoader classLoader, String str);

            /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy$ManifestReading$SealBaseLocator$NonSealing.class */
            public enum NonSealing implements SealBaseLocator {
                INSTANCE;

                @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.ManifestReading.SealBaseLocator
                @MaybeNull
                public URL findSealBase(ClassLoader classLoader, String typeName) {
                    return ManifestReading.NOT_SEALED;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy$ManifestReading$SealBaseLocator$ForFixedValue.class */
            public static class ForFixedValue implements SealBaseLocator {
                @MaybeNull
                @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
                private final URL sealBase;

                public ForFixedValue(@MaybeNull URL sealBase) {
                    this.sealBase = sealBase;
                }

                @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.ManifestReading.SealBaseLocator
                @MaybeNull
                public URL findSealBase(ClassLoader classLoader, String typeName) {
                    return this.sealBase;
                }

                @SuppressFBWarnings(value = {"DMI_BLOCKING_METHODS_ON_URL"}, justification = "Package sealing relies on URL equality.")
                public int hashCode() {
                    if (this.sealBase == null) {
                        return 17;
                    }
                    return this.sealBase.hashCode();
                }

                @SuppressFBWarnings(value = {"DMI_BLOCKING_METHODS_ON_URL"}, justification = "Package sealing relies on URL equality.")
                public boolean equals(@MaybeNull Object other) {
                    if (this == other) {
                        return true;
                    }
                    if (other == null || getClass() != other.getClass()) {
                        return false;
                    }
                    ForFixedValue forFixedValue = (ForFixedValue) other;
                    if (this.sealBase == null) {
                        return forFixedValue.sealBase == null;
                    }
                    return this.sealBase.equals(forFixedValue.sealBase);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy$ManifestReading$SealBaseLocator$ForTypeResourceUrl.class */
            public static class ForTypeResourceUrl implements SealBaseLocator {
                private static final int EXCLUDE_INITIAL_SLASH = 1;
                private static final String CLASS_FILE_EXTENSION = ".class";
                private static final String JAR_FILE = "jar";
                private static final String FILE_SYSTEM = "file";
                private static final String RUNTIME_IMAGE = "jrt";
                private final SealBaseLocator fallback;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.fallback.equals(((ForTypeResourceUrl) obj).fallback);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.fallback.hashCode();
                }

                public ForTypeResourceUrl() {
                    this(NonSealing.INSTANCE);
                }

                public ForTypeResourceUrl(SealBaseLocator fallback) {
                    this.fallback = fallback;
                }

                @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.ManifestReading.SealBaseLocator
                @MaybeNull
                public URL findSealBase(ClassLoader classLoader, String typeName) {
                    URL url = classLoader.getResource(typeName.replace('.', '/') + ".class");
                    if (url != null) {
                        try {
                            if (url.getProtocol().equals("jar")) {
                                return new URL(url.getPath().substring(0, url.getPath().indexOf(33)));
                            }
                            if (url.getProtocol().equals("file")) {
                                return url;
                            }
                            if (url.getProtocol().equals(RUNTIME_IMAGE)) {
                                String path = url.getPath();
                                int modulePathIndex = path.indexOf(47, 1);
                                return modulePathIndex == -1 ? url : new URL("jrt:" + path.substring(0, modulePathIndex));
                            }
                        } catch (MalformedURLException exception) {
                            throw new IllegalStateException("Unexpected URL: " + url, exception);
                        }
                    }
                    return this.fallback.findSealBase(classLoader, typeName);
                }
            }
        }
    }
}