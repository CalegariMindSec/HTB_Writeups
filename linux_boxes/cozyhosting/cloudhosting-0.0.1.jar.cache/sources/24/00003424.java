package org.hibernate.event.service.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/event/service/spi/DuplicationStrategy.class */
public interface DuplicationStrategy {

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/event/service/spi/DuplicationStrategy$Action.class */
    public enum Action {
        ERROR,
        KEEP_ORIGINAL,
        REPLACE_ORIGINAL
    }

    boolean areMatch(Object obj, Object obj2);

    Action getAction();
}