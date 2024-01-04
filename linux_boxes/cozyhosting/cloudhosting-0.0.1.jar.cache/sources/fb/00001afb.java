package org.antlr.v4.runtime.misc;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/antlr4-runtime-4.10.1.jar:org/antlr/v4/runtime/misc/DoubleKeyMap.class */
public class DoubleKeyMap<Key1, Key2, Value> {
    Map<Key1, Map<Key2, Value>> data = new LinkedHashMap();

    public Value put(Key1 k1, Key2 k2, Value v) {
        Map<Key2, Value> data2 = this.data.get(k1);
        Value prev = null;
        if (data2 == null) {
            data2 = new LinkedHashMap<>();
            this.data.put(k1, data2);
        } else {
            prev = data2.get(k2);
        }
        data2.put(k2, v);
        return prev;
    }

    public Value get(Key1 k1, Key2 k2) {
        Map<Key2, Value> data2 = this.data.get(k1);
        if (data2 == null) {
            return null;
        }
        return data2.get(k2);
    }

    public Map<Key2, Value> get(Key1 k1) {
        return this.data.get(k1);
    }

    public Collection<Value> values(Key1 k1) {
        Map<Key2, Value> data2 = this.data.get(k1);
        if (data2 == null) {
            return null;
        }
        return data2.values();
    }

    public Set<Key1> keySet() {
        return this.data.keySet();
    }

    public Set<Key2> keySet(Key1 k1) {
        Map<Key2, Value> data2 = this.data.get(k1);
        if (data2 == null) {
            return null;
        }
        return data2.keySet();
    }
}