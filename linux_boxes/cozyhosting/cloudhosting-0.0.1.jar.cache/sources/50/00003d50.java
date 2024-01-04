package org.hibernate.service.internal;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/service/internal/ProvidedService.class */
public class ProvidedService<R> {
    private final Class<R> serviceRole;
    private final R service;

    public ProvidedService(Class<R> serviceRole, R service) {
        this.serviceRole = serviceRole;
        this.service = service;
    }

    public Class<R> getServiceRole() {
        return this.serviceRole;
    }

    public R getService() {
        return this.service;
    }
}