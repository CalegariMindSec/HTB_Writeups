package org.hibernate.resource.beans.container.spi;

import jakarta.enterprise.inject.spi.BeanManager;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/resource/beans/container/spi/ExtendedBeanManager.class */
public interface ExtendedBeanManager {

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/resource/beans/container/spi/ExtendedBeanManager$LifecycleListener.class */
    public interface LifecycleListener {
        void beanManagerInitialized(BeanManager beanManager);

        void beforeBeanManagerDestroyed(BeanManager beanManager);
    }

    void registerLifecycleListener(LifecycleListener lifecycleListener);
}