package org.hibernate.query.sqm.tree;

import java.util.IdentityHashMap;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/query/sqm/tree/SqmCopyContext.class */
public interface SqmCopyContext {
    <T> T getCopy(T t);

    <T> T registerCopy(T t, T t2);

    static SqmCopyContext simpleContext() {
        final IdentityHashMap<Object, Object> map = new IdentityHashMap<>();
        return new SqmCopyContext() { // from class: org.hibernate.query.sqm.tree.SqmCopyContext.1
            @Override // org.hibernate.query.sqm.tree.SqmCopyContext
            public <T> T getCopy(T original) {
                return (T) map.get(original);
            }

            @Override // org.hibernate.query.sqm.tree.SqmCopyContext
            public <T> T registerCopy(T original, T copy) {
                Object old = map.put(original, copy);
                if (old != null) {
                    throw new IllegalArgumentException("Already registered a copy: " + old);
                }
                return copy;
            }
        };
    }
}