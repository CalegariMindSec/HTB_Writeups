package org.hibernate.stat;

import java.util.Set;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/stat/SessionStatistics.class */
public interface SessionStatistics {
    int getEntityCount();

    int getCollectionCount();

    Set<?> getEntityKeys();

    Set<?> getCollectionKeys();
}