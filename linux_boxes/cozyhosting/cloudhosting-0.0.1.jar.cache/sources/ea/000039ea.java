package org.hibernate.query;

import org.hibernate.Incubating;

@FunctionalInterface
@Incubating
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/query/TupleTransformer.class */
public interface TupleTransformer<T> {
    T transformTuple(Object[] objArr, String[] strArr);
}