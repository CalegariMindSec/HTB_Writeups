package org.hibernate.service.spi;

import org.hibernate.service.Service;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/service/spi/ServiceInitiator.class */
public interface ServiceInitiator<R extends Service> {
    Class<R> getServiceInitiated();
}