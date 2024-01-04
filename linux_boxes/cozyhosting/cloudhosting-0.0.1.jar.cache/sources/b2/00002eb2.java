package org.hibernate.boot.model.source.spi;

import java.lang.annotation.Annotation;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/model/source/spi/JpaCallbackSource.class */
public interface JpaCallbackSource {
    String getCallbackMethod(Class<? extends Annotation> cls);

    String getName();

    boolean isListener();
}