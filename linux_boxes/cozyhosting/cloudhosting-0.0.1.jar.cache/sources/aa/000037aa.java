package org.hibernate.mapping;

@FunctionalInterface
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/mapping/IndexedBiConsumer.class */
public interface IndexedBiConsumer<T, U> {
    void accept(int i, T t, U u);
}