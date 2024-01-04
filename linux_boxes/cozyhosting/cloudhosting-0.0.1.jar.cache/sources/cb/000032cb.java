package org.hibernate.engine.jdbc.batch.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/jdbc/batch/spi/BatchObserver.class */
public interface BatchObserver {
    void batchExplicitlyExecuted();

    void batchImplicitlyExecuted();
}