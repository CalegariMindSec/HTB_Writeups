package org.hibernate.event.service.spi;

import org.hibernate.Incubating;

@FunctionalInterface
@Incubating
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/event/service/spi/EventActionWithParameter.class */
public interface EventActionWithParameter<T, U, X> {
    void applyEventToListener(T t, U u, X x);
}