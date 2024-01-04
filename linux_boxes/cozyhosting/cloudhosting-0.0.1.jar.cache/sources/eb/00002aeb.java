package org.hibernate.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.hibernate.Remove;

@java.lang.annotation.Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Remove
@Deprecated(forRemoval = true)
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/annotations/ForeignKey.class */
public @interface ForeignKey {
    String name();

    String inverseName() default "";
}