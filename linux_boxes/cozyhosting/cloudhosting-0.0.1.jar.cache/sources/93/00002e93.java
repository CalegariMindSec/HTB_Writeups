package org.hibernate.boot.model.source.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/model/source/spi/ColumnBindingDefaults.class */
public interface ColumnBindingDefaults {
    boolean areValuesIncludedInInsertByDefault();

    boolean areValuesIncludedInUpdateByDefault();

    boolean areValuesNullableByDefault();
}