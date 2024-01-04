package org.hibernate.boot.archive.scan.spi;

import java.net.URL;
import java.util.List;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/archive/scan/spi/ScanEnvironment.class */
public interface ScanEnvironment {
    URL getRootUrl();

    List<URL> getNonRootUrls();

    List<String> getExplicitlyListedClassNames();

    List<String> getExplicitlyListedMappingFiles();
}