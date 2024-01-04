package org.hibernate;

@Incubating
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/TimeZoneStorageStrategy.class */
public enum TimeZoneStorageStrategy {
    NATIVE,
    COLUMN,
    NORMALIZE,
    NORMALIZE_UTC
}