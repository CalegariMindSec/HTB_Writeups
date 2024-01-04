package org.hibernate.engine.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/spi/EntityEntryExtraState.class */
public interface EntityEntryExtraState {
    void addExtraState(EntityEntryExtraState entityEntryExtraState);

    <T extends EntityEntryExtraState> T getExtraState(Class<T> cls);
}