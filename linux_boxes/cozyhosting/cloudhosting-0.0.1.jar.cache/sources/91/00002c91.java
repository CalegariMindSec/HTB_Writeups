package org.hibernate.boot.jaxb.hbm.transform;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/transform/ColumnDefaults.class */
interface ColumnDefaults {
    Boolean isNullable();

    Integer getLength();

    Integer getScale();

    Integer getPrecision();

    Boolean isUnique();

    Boolean isInsertable();

    Boolean isUpdateable();
}