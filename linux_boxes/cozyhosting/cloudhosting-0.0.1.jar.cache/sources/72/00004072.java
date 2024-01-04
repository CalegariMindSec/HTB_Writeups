package org.hibernate.type.descriptor.java;

import java.util.Comparator;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/type/descriptor/java/IncomparableComparator.class */
public class IncomparableComparator implements Comparator {
    public static final IncomparableComparator INSTANCE = new IncomparableComparator();

    @Override // java.util.Comparator
    public int compare(Object o1, Object o2) {
        return 0;
    }
}