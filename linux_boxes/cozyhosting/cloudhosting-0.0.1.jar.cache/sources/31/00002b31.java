package org.hibernate.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@java.lang.annotation.Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/annotations/SelectBeforeUpdate.class */
public @interface SelectBeforeUpdate {
    @Deprecated(since = "6.0")
    boolean value() default true;
}