package org.hibernate.boot.registry.selector.internal;

@FunctionalInterface
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/registry/selector/internal/LazyServiceResolver.class */
public interface LazyServiceResolver<T> {
    Class<? extends T> resolve(String str);
}