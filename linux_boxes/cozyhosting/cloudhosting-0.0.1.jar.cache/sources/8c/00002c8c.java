package org.hibernate.boot.jaxb.hbm.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/TableInformationContainer.class */
public interface TableInformationContainer {
    String getSchema();

    String getCatalog();

    String getTable();

    String getSubselect();

    String getSubselectAttribute();
}