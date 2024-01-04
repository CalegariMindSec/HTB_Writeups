package org.hibernate.boot;

import java.io.InputStream;

@FunctionalInterface
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/ResourceStreamLocator.class */
public interface ResourceStreamLocator {
    InputStream locateResourceStream(String str);
}