package org.hibernate.engine.internal;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/internal/CascadePoint.class */
public enum CascadePoint {
    AFTER_INSERT_BEFORE_DELETE,
    BEFORE_INSERT_AFTER_DELETE,
    AFTER_INSERT_BEFORE_DELETE_VIA_COLLECTION,
    AFTER_UPDATE,
    BEFORE_FLUSH,
    AFTER_EVICT,
    BEFORE_REFRESH,
    AFTER_LOCK,
    BEFORE_MERGE
}