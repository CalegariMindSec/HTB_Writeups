package org.hibernate.cache.spi.entry;

import java.io.Serializable;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/cache/spi/entry/CacheEntry.class */
public interface CacheEntry extends Serializable {
    boolean isReferenceEntry();

    String getSubclass();

    Object getVersion();

    Serializable[] getDisassembledState();
}