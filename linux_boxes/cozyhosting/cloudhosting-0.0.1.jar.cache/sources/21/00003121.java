package org.hibernate.context.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/context/spi/CurrentTenantIdentifierResolver.class */
public interface CurrentTenantIdentifierResolver {
    String resolveCurrentTenantIdentifier();

    boolean validateExistingCurrentSessions();

    default boolean isRoot(String tenantId) {
        return false;
    }
}