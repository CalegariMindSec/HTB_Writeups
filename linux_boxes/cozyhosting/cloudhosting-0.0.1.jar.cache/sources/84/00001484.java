package net.bytebuddy.dynamic.loading;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.AllPermission;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.ProtectionDomain;
import java.util.Collections;
import java.util.Enumeration;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.utility.nullability.AlwaysNull;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/dynamic/loading/ClassFilePostProcessor.class */
public interface ClassFilePostProcessor {
    byte[] transform(@MaybeNull ClassLoader classLoader, String str, @MaybeNull ProtectionDomain protectionDomain, byte[] bArr);

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/dynamic/loading/ClassFilePostProcessor$NoOp.class */
    public enum NoOp implements ClassFilePostProcessor {
        INSTANCE;

        @Override // net.bytebuddy.dynamic.loading.ClassFilePostProcessor
        public byte[] transform(@MaybeNull ClassLoader classLoader, String name, @MaybeNull ProtectionDomain protectionDomain, byte[] binaryRepresentation) {
            return binaryRepresentation;
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/dynamic/loading/ClassFilePostProcessor$ForClassFileTransformer.class */
    public static class ForClassFileTransformer implements ClassFilePostProcessor {
        protected static final ProtectionDomain ALL_PRIVILEGES = new ProtectionDomain(null, new AllPermissionsCollection());
        @AlwaysNull
        private static final Class<?> UNLOADED_TYPE = null;
        private final ClassFileTransformer classFileTransformer;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.classFileTransformer.equals(((ForClassFileTransformer) obj).classFileTransformer);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.classFileTransformer.hashCode();
        }

        public ForClassFileTransformer(ClassFileTransformer classFileTransformer) {
            this.classFileTransformer = classFileTransformer;
        }

        @Override // net.bytebuddy.dynamic.loading.ClassFilePostProcessor
        public byte[] transform(@MaybeNull ClassLoader classLoader, String name, @MaybeNull ProtectionDomain protectionDomain, byte[] binaryRepresentation) {
            try {
                byte[] transformed = this.classFileTransformer.transform(classLoader, name.replace('.', '/'), UNLOADED_TYPE, protectionDomain == null ? ALL_PRIVILEGES : protectionDomain, binaryRepresentation);
                return transformed == null ? binaryRepresentation : transformed;
            } catch (IllegalClassFormatException exception) {
                throw new IllegalStateException("Failed to transform " + name, exception);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/dynamic/loading/ClassFilePostProcessor$ForClassFileTransformer$AllPermissionsCollection.class */
        protected static class AllPermissionsCollection extends PermissionCollection {
            private static final long serialVersionUID = 1;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass();
            }

            public int hashCode() {
                return getClass().hashCode();
            }

            protected AllPermissionsCollection() {
            }

            @Override // java.security.PermissionCollection
            public void add(Permission permission) {
                throw new UnsupportedOperationException(BeanUtil.PREFIX_ADDER);
            }

            @Override // java.security.PermissionCollection
            public boolean implies(Permission permission) {
                return true;
            }

            @Override // java.security.PermissionCollection
            public Enumeration<Permission> elements() {
                return Collections.enumeration(Collections.singleton(new AllPermission()));
            }
        }
    }
}