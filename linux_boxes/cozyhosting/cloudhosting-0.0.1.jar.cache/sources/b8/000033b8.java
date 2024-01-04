package org.hibernate.engine.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/spi/UnsavedValueStrategy.class */
public interface UnsavedValueStrategy {
    Boolean isUnsaved(Object obj);

    Object getDefaultValue(Object obj);
}