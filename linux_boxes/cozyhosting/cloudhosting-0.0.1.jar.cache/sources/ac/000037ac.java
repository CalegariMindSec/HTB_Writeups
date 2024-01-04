package org.hibernate.mapping;

@FunctionalInterface
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/mapping/IndexedConsumer.class */
public interface IndexedConsumer<T> {
    void accept(int i, T t);
}