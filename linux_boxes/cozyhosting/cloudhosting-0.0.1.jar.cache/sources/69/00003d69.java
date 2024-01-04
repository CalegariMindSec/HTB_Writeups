package org.hibernate.service.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/service/spi/Wrapped.class */
public interface Wrapped {
    boolean isUnwrappableAs(Class<?> cls);

    <T> T unwrap(Class<T> cls);
}