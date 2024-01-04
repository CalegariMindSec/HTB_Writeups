package org.hibernate.resource.beans.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/resource/beans/spi/ManagedBean.class */
public interface ManagedBean<T> {
    Class<T> getBeanClass();

    T getBeanInstance();
}