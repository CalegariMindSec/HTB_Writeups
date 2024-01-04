package org.hibernate.persister.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/persister/spi/HydratedCompoundValueHandler.class */
public interface HydratedCompoundValueHandler {
    Object extract(Object obj);

    void inject(Object obj, Object obj2);
}