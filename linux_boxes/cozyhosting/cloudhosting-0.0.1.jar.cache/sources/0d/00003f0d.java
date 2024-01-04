package org.hibernate.sql.results.jdbc.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/sql/results/jdbc/spi/JdbcValuesSourceProcessingOptions.class */
public interface JdbcValuesSourceProcessingOptions {
    Object getEffectiveOptionalObject();

    String getEffectiveOptionalEntityName();

    Object getEffectiveOptionalId();

    boolean shouldReturnProxies();
}