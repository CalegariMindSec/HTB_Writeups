package org.hibernate.boot.model.naming;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/model/naming/EntityNaming.class */
public interface EntityNaming {
    String getClassName();

    String getEntityName();

    String getJpaEntityName();
}