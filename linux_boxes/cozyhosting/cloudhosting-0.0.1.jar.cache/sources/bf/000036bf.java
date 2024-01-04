package org.hibernate.internal.util.collections;

import java.util.Arrays;
import java.util.function.Function;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/LazyIndexedMap.class */
public abstract class LazyIndexedMap<K, V> {
    private volatile Object[] values;
    private static final Object NOT_INITIALIZED = new Object();

    /* JADX INFO: Access modifiers changed from: protected */
    public LazyIndexedMap(int size) {
        Object[] vs = new Object[size];
        Arrays.fill(vs, NOT_INITIALIZED);
        this.values = vs;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <K1 extends K> V computeIfAbsent(int index, K1 originalKey, Function<K1, V> valueGenerator) {
        V v = (V) this.values[index];
        if (v != NOT_INITIALIZED) {
            return v;
        }
        return lockedComputeIfAbsent(index, originalKey, valueGenerator);
    }

    private synchronized <K1 extends K> V lockedComputeIfAbsent(int index, K1 originalKey, Function<K1, V> valueGenerator) {
        Object[] values = this.values;
        V v = (V) values[index];
        if (v != NOT_INITIALIZED) {
            return v;
        }
        V generated = valueGenerator.apply(originalKey);
        values[index] = generated;
        this.values = values;
        return generated;
    }
}