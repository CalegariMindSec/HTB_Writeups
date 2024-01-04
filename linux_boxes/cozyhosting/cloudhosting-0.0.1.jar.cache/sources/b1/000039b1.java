package org.hibernate.property.access.spi;

import java.io.Serializable;
import java.lang.reflect.Method;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/property/access/spi/Setter.class */
public interface Setter extends Serializable {
    void set(Object obj, Object obj2);

    String getMethodName();

    Method getMethod();
}