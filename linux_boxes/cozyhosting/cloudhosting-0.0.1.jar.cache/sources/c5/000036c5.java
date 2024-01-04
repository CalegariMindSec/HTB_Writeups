package org.hibernate.internal.util.compare;

import java.io.Serializable;
import java.lang.Comparable;
import java.util.Comparator;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/compare/ComparableComparator.class */
public class ComparableComparator<T extends Comparable> implements Comparator<T>, Serializable {
    public static final Comparator INSTANCE = new ComparableComparator();

    public static <T extends Comparable> Comparator<T> instance() {
        return INSTANCE;
    }

    @Override // java.util.Comparator
    public int compare(Comparable one, Comparable another) {
        return one.compareTo(another);
    }
}