package org.hibernate.boot.archive.spi;

import java.net.URL;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/archive/spi/JarFileEntryUrlAdjuster.class */
public interface JarFileEntryUrlAdjuster {
    URL adjustJarFileEntryUrl(URL url, URL url2);
}