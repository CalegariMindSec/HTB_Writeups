package org.hibernate.engine.transaction.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/transaction/spi/TransactionObserver.class */
public interface TransactionObserver {
    void afterBegin();

    void beforeCompletion();

    void afterCompletion(boolean z, boolean z2);
}