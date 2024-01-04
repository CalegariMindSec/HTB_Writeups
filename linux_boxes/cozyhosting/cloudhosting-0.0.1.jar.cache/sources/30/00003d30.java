package org.hibernate.resource.transaction.backend.jta.internal.synchronization;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/resource/transaction/backend/jta/internal/synchronization/SynchronizationCallbackTarget.class */
public interface SynchronizationCallbackTarget {
    boolean isActive();

    void beforeCompletion();

    void afterCompletion(boolean z, boolean z2);
}