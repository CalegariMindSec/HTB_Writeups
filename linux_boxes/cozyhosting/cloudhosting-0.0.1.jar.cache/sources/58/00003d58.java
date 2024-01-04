package org.hibernate.service.spi;

import java.util.Map;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/service/spi/Configurable.class */
public interface Configurable {
    void configure(Map<String, Object> map);
}