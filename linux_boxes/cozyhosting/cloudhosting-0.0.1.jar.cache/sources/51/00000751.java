package com.zaxxer.hikari.metrics;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/HikariCP-5.0.1.jar:com/zaxxer/hikari/metrics/IMetricsTracker.class */
public interface IMetricsTracker extends AutoCloseable {
    default void recordConnectionCreatedMillis(long connectionCreatedMillis) {
    }

    default void recordConnectionAcquiredNanos(long elapsedAcquiredNanos) {
    }

    default void recordConnectionUsageMillis(long elapsedBorrowedMillis) {
    }

    default void recordConnectionTimeout() {
    }

    @Override // java.lang.AutoCloseable
    default void close() {
    }
}