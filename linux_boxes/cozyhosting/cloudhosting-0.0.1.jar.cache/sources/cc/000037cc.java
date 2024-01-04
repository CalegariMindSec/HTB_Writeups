package org.hibernate.mapping;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/mapping/SortableValue.class */
public interface SortableValue {
    boolean isSorted();

    int[] sortProperties();
}