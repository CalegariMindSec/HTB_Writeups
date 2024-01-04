package org.hibernate.jpa.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/jpa/spi/JpaCompliance.class */
public interface JpaCompliance {
    boolean isJpaQueryComplianceEnabled();

    boolean isJpaTransactionComplianceEnabled();

    @Deprecated(since = "6.0")
    boolean isJpaListComplianceEnabled();

    boolean isJpaClosedComplianceEnabled();

    boolean isJpaProxyComplianceEnabled();

    boolean isJpaCacheComplianceEnabled();

    boolean isGlobalGeneratorScopeEnabled();

    boolean isJpaOrderByMappingComplianceEnabled();

    boolean isLoadByIdComplianceEnabled();
}