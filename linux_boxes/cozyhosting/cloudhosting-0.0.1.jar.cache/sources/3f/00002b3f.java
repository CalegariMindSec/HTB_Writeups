package org.hibernate.annotations;

import org.hibernate.Incubating;

@Incubating
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/annotations/TimeZoneStorageType.class */
public enum TimeZoneStorageType {
    NATIVE,
    NORMALIZE,
    NORMALIZE_UTC,
    COLUMN,
    AUTO
}