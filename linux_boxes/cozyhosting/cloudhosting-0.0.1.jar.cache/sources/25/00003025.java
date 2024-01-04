package org.hibernate.cache.spi.support;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/cache/spi/support/AccessedDataClassification.class */
public enum AccessedDataClassification {
    ENTITY,
    NATURAL_ID,
    COLLECTION,
    QUERY_RESULTS,
    TIMESTAMPS
}