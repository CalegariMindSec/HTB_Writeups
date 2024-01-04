package org.hibernate.collection.spi;

import org.hibernate.Incubating;

@Incubating
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/collection/spi/LazyInitializable.class */
public interface LazyInitializable {
    boolean wasInitialized();

    void forceInitialization();
}