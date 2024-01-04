package org.hibernate.metamodel.spi;

import org.hibernate.Incubating;

@Incubating
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/metamodel/spi/ValueAccess.class */
public interface ValueAccess {
    Object[] getValues();

    default <T> T getValue(int i, Class<T> clazz) {
        return clazz.cast(getValues()[i]);
    }

    default Object getOwner() {
        return null;
    }
}