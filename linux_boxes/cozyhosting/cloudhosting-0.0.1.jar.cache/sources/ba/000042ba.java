package org.postgresql.core;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/core/JdbcCallParseInfo.class */
public class JdbcCallParseInfo {
    private final String sql;
    private final boolean isFunction;

    public JdbcCallParseInfo(String sql, boolean isFunction) {
        this.sql = sql;
        this.isFunction = isFunction;
    }

    public String getSql() {
        return this.sql;
    }

    public boolean isFunction() {
        return this.isFunction;
    }
}