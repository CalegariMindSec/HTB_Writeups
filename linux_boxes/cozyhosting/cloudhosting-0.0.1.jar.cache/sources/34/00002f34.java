package org.hibernate.boot.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/spi/JpaOrmXmlPersistenceUnitDefaultAware.class */
public interface JpaOrmXmlPersistenceUnitDefaultAware {

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/spi/JpaOrmXmlPersistenceUnitDefaultAware$JpaOrmXmlPersistenceUnitDefaults.class */
    public interface JpaOrmXmlPersistenceUnitDefaults {
        String getDefaultSchemaName();

        String getDefaultCatalogName();

        boolean shouldImplicitlyQuoteIdentifiers();
    }

    void apply(JpaOrmXmlPersistenceUnitDefaults jpaOrmXmlPersistenceUnitDefaults);
}