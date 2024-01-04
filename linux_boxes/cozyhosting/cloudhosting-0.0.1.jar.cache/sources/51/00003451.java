package org.hibernate.event.spi;

import java.util.IdentityHashMap;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/event/spi/PersistContext.class */
public interface PersistContext {
    boolean add(Object obj);

    /* renamed from: org.hibernate.event.spi.PersistContext$1Impl  reason: invalid class name */
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/event/spi/PersistContext$1Impl.class */
    class C1Impl extends IdentityHashMap<Object, Object> implements PersistContext {
        C1Impl() {
            super(10);
        }

        @Override // org.hibernate.event.spi.PersistContext
        public boolean add(Object entity) {
            return put(entity, entity) == null;
        }
    }

    static PersistContext create() {
        return new C1Impl();
    }
}