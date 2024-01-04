package org.hibernate.bytecode.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/bytecode/spi/ReflectionOptimizer.class */
public interface ReflectionOptimizer {

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/bytecode/spi/ReflectionOptimizer$AccessOptimizer.class */
    public interface AccessOptimizer {
        String[] getPropertyNames();

        Object[] getPropertyValues(Object obj);

        void setPropertyValues(Object obj, Object[] objArr);
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/bytecode/spi/ReflectionOptimizer$InstantiationOptimizer.class */
    public interface InstantiationOptimizer {
        Object newInstance();
    }

    InstantiationOptimizer getInstantiationOptimizer();

    AccessOptimizer getAccessOptimizer();
}