package org.hibernate.engine.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/spi/Resolution.class */
public interface Resolution {
    Object getNaturalIdValue();

    boolean isSame(Object obj);
}