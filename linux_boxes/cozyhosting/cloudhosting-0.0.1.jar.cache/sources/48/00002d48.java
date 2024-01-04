package org.hibernate.boot.jaxb.mapping;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/mapping/SchemaAware.class */
public interface SchemaAware {
    String getSchema();

    void setSchema(String str);

    String getCatalog();

    void setCatalog(String str);
}