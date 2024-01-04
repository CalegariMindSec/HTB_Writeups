package org.postgresql.core;

import net.bytebuddy.ClassFileVersion;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/core/JavaVersion.class */
public enum JavaVersion {
    v1_8,
    other;
    
    private static final JavaVersion RUNTIME_VERSION = from(System.getProperty(ClassFileVersion.VersionLocator.JAVA_VERSION));

    public static JavaVersion getRuntimeVersion() {
        return RUNTIME_VERSION;
    }

    public static JavaVersion from(String version) {
        if (version.startsWith("1.8")) {
            return v1_8;
        }
        return other;
    }
}