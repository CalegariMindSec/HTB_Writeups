package org.hibernate.boot.jaxb.hbm.transform;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/transform/SourceColumnAdapter.class */
public interface SourceColumnAdapter {
    String getName();

    Boolean isNotNull();

    Boolean isUnique();

    Integer getLength();

    Integer getPrecision();

    Integer getScale();

    String getSqlType();

    String getComment();

    String getCheck();

    String getDefault();

    String getIndex();

    String getUniqueKey();

    String getRead();

    String getWrite();
}