package org.hibernate.sql.results.spi;

import org.hibernate.Incubating;

@Incubating
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/sql/results/spi/RowTransformer.class */
public interface RowTransformer<T> {
    T transformRow(Object[] objArr);

    default int determineNumberOfResultElements(int rawElementCount) {
        return rawElementCount;
    }
}