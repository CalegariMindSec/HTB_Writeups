package org.hibernate.cache.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/cache/spi/CacheTransactionSynchronization.class */
public interface CacheTransactionSynchronization {
    @Deprecated
    long getCurrentTransactionStartTimestamp();

    void transactionJoined();

    void transactionCompleting();

    void transactionCompleted(boolean z);

    default long getCachingTimestamp() {
        return getCurrentTransactionStartTimestamp();
    }

    default void transactionSuspended() {
    }

    default void transactionResumed() {
    }
}