package com.zaxxer.hikari.util;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/HikariCP-5.0.1.jar:com/zaxxer/hikari/util/IsolationLevel.class */
public enum IsolationLevel {
    TRANSACTION_NONE(0),
    TRANSACTION_READ_UNCOMMITTED(1),
    TRANSACTION_READ_COMMITTED(2),
    TRANSACTION_REPEATABLE_READ(4),
    TRANSACTION_SERIALIZABLE(8),
    TRANSACTION_SQL_SERVER_SNAPSHOT_ISOLATION_LEVEL(4096);
    
    private final int levelId;

    IsolationLevel(int levelId) {
        this.levelId = levelId;
    }

    public int getLevelId() {
        return this.levelId;
    }
}