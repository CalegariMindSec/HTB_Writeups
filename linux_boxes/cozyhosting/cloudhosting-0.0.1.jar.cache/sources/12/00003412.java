package org.hibernate.event.internal;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/event/internal/EventUtil.class */
class EventUtil {
    EventUtil() {
    }

    public static String getLoggableName(String entityName, Object entity) {
        return entityName == null ? entity.getClass().getName() : entityName;
    }
}