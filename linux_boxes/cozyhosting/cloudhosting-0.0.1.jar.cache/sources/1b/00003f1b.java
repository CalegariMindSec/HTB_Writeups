package org.hibernate.stat;

import java.io.Serializable;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/stat/CacheRegionStatistics.class */
public interface CacheRegionStatistics extends Serializable {
    public static final long NO_EXTENDED_STAT_SUPPORT_RETURN = Long.MIN_VALUE;

    String getRegionName();

    long getPutCount();

    long getHitCount();

    long getMissCount();

    long getElementCountInMemory();

    long getElementCountOnDisk();

    long getSizeInMemory();
}