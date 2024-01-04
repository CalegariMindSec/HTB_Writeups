package org.hibernate.boot.registry.selector.spi;

@FunctionalInterface
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/registry/selector/spi/StrategyCreator.class */
public interface StrategyCreator<T> {
    T create(Class<? extends T> cls);
}