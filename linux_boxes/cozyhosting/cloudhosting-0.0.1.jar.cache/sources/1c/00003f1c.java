package org.hibernate.stat;

import java.io.Serializable;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/stat/CacheableDataStatistics.class */
public interface CacheableDataStatistics extends Serializable {
    public static final long NOT_CACHED_COUNT = Long.MIN_VALUE;

    String getCacheRegionName();

    long getCachePutCount();

    long getCacheHitCount();

    long getCacheMissCount();
}