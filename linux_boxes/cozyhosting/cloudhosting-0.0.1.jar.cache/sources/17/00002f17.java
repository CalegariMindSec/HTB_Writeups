package org.hibernate.boot.registry.selector;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/registry/selector/StrategyRegistration.class */
public interface StrategyRegistration<T> {
    Class<T> getStrategyRole();

    Iterable<String> getSelectorNames();

    Class<? extends T> getStrategyImplementation();
}