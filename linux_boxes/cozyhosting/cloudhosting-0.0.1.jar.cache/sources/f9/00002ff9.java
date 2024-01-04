package org.hibernate.cache.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/cache/spi/ExtendedStatisticsSupport.class */
public interface ExtendedStatisticsSupport {
    long getElementCountInMemory();

    long getElementCountOnDisk();

    long getSizeInMemory();
}