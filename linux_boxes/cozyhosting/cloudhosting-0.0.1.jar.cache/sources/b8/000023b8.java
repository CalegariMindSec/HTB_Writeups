package org.aspectj.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/util/SoftHashMap.class */
public class SoftHashMap<K, V> extends AbstractMap<K, V> {
    private ReferenceQueue<? super V> rq = new ReferenceQueue<>();
    private Map<K, SoftHashMap<K, V>.SpecialValue> map = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/util/SoftHashMap$SpecialValue.class */
    public class SpecialValue extends SoftReference<V> {
        private final K key;

        SpecialValue(K k, V v) {
            super(v, SoftHashMap.this.rq);
            this.key = k;
        }
    }

    private void processQueue() {
        while (true) {
            SoftHashMap<K, V>.SpecialValue sv = (SpecialValue) this.rq.poll();
            if (sv != null) {
                this.map.remove(((SpecialValue) sv).key);
            } else {
                return;
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object key) {
        SoftHashMap<K, V>.SpecialValue ref = this.map.get(key);
        if (ref == null) {
            this.map.remove(key);
            return null;
        }
        V value = ref.get();
        if (value == null) {
            this.map.remove(((SpecialValue) ref).key);
            return null;
        }
        return value;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        processQueue();
        SoftHashMap<K, V>.SpecialValue sv = new SpecialValue(k, v);
        SoftHashMap<K, V>.SpecialValue result = this.map.put(k, sv);
        if (result == null) {
            return null;
        }
        return result.get();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.map.isEmpty()) {
            return Collections.emptyMap().entrySet();
        }
        Map<K, V> currentContents = new HashMap<>();
        for (Map.Entry<K, SoftHashMap<K, V>.SpecialValue> entry : this.map.entrySet()) {
            V currentValueForEntry = entry.getValue().get();
            if (currentValueForEntry != null) {
                currentContents.put(entry.getKey(), currentValueForEntry);
            }
        }
        return currentContents.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        processQueue();
        this.map.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        processQueue();
        return this.map.size();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object k) {
        processQueue();
        SoftHashMap<K, V>.SpecialValue ref = this.map.remove(k);
        if (ref == null) {
            return null;
        }
        return ref.get();
    }
}