package org.hibernate.loader;

@FunctionalInterface
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/loader/BatchLoadSizingStrategy.class */
public interface BatchLoadSizingStrategy {
    int determineOptimalBatchLoadSize(int i, int i2, boolean z);
}