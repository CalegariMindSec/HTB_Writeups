package org.hibernate.boot.archive.scan.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/archive/scan/spi/ScanOptions.class */
public interface ScanOptions {
    boolean canDetectUnlistedClassesInRoot();

    boolean canDetectUnlistedClassesInNonRoot();

    @Deprecated
    boolean canDetectHibernateMappingFiles();
}