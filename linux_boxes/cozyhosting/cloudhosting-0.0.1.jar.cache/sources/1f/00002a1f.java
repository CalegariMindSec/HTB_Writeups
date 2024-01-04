package org.glassfish.jaxb.runtime.v2.util;

import java.util.Map;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jaxb-runtime-4.0.1.jar:org/glassfish/jaxb/runtime/v2/util/TypeCast.class */
public class TypeCast {
    private TypeCast() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <K, V> Map<K, V> checkedCast(Map<?, ?> m, Class<K> keyType, Class<V> valueType) {
        if (m == 0) {
            return null;
        }
        for (Map.Entry<?, ?> e : m.entrySet()) {
            if (!keyType.isInstance(e.getKey())) {
                throw new ClassCastException(e.getKey().getClass().toString());
            }
            if (!valueType.isInstance(e.getValue())) {
                throw new ClassCastException(e.getValue().getClass().toString());
            }
        }
        return m;
    }
}