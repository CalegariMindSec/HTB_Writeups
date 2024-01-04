package org.hibernate.query.sqm;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/query/sqm/CastType.class */
public enum CastType {
    STRING,
    BOOLEAN,
    INTEGER_BOOLEAN,
    YN_BOOLEAN,
    TF_BOOLEAN,
    INTEGER,
    LONG,
    FLOAT,
    DOUBLE,
    FIXED,
    DATE,
    TIME,
    TIMESTAMP,
    OFFSET_TIMESTAMP,
    ZONE_TIMESTAMP,
    NULL,
    OTHER;

    public boolean isNumeric() {
        switch (this) {
            case INTEGER:
            case LONG:
            case INTEGER_BOOLEAN:
            case FLOAT:
            case DOUBLE:
            case FIXED:
                return true;
            default:
                return false;
        }
    }
}