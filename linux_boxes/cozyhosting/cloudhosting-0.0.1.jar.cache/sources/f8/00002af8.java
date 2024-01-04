package org.hibernate.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.hibernate.type.descriptor.java.BasicJavaType;

@java.lang.annotation.Target({ElementType.PACKAGE, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(JavaTypeRegistrations.class)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/annotations/JavaTypeRegistration.class */
public @interface JavaTypeRegistration {
    Class<?> javaType();

    Class<? extends BasicJavaType<?>> descriptorClass();
}