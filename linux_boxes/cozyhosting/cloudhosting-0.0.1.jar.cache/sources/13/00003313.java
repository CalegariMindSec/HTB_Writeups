package org.hibernate.engine.jdbc.env.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/jdbc/env/spi/SQLStateType.class */
public enum SQLStateType {
    XOpen,
    SQL99,
    UNKNOWN;

    public static SQLStateType interpretReportedSQLStateType(int sqlStateType) {
        switch (sqlStateType) {
            case 1:
                return XOpen;
            case 2:
                return SQL99;
            default:
                return UNKNOWN;
        }
    }
}