package org.hibernate.boot.model.source.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/model/source/spi/TableSpecificationSource.class */
public interface TableSpecificationSource {
    String getExplicitSchemaName();

    String getExplicitCatalogName();

    String getComment();
}