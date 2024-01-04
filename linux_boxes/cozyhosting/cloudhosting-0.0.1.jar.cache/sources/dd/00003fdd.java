package org.hibernate.tuple;

import java.io.Serializable;

@Deprecated(since = "6.0")
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/tuple/Instantiator.class */
public interface Instantiator extends Serializable {
    Object instantiate(Object obj);

    Object instantiate();

    boolean isInstance(Object obj);
}