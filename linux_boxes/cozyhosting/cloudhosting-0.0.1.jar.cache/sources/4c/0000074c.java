package com.zaxxer.hikari;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/HikariCP-5.0.1.jar:com/zaxxer/hikari/HikariPoolMXBean.class */
public interface HikariPoolMXBean {
    int getIdleConnections();

    int getActiveConnections();

    int getTotalConnections();

    int getThreadsAwaitingConnection();

    void softEvictConnections();

    void suspendPool();

    void resumePool();
}