package org.hibernate.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@java.lang.annotation.Target({ElementType.PACKAGE, ElementType.TYPE})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(JdbcTypeRegistrations.class)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/annotations/JdbcTypeRegistration.class */
public @interface JdbcTypeRegistration {
    Class<? extends org.hibernate.type.descriptor.jdbc.JdbcType> value();

    int registrationCode() default Integer.MIN_VALUE;
}