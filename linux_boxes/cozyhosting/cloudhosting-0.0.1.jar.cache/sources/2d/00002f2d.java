package org.hibernate.boot.spi;

import java.net.URL;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/spi/ClassLoaderAccess.class */
public interface ClassLoaderAccess {
    <T> Class<T> classForName(String str);

    URL locateResource(String str);
}