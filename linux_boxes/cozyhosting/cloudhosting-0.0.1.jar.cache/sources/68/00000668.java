package com.fasterxml.jackson.databind.util;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jackson-databind-2.14.1.jar:com/fasterxml/jackson/databind/util/LookupCache.class */
public interface LookupCache<K, V> {
    int size();

    V get(Object obj);

    V put(K k, V v);

    V putIfAbsent(K k, V v);

    void clear();
}