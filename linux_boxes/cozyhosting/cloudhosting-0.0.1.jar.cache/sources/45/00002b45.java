package org.hibernate.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@java.lang.annotation.Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/annotations/Where.class */
public @interface Where {
    String clause();
}