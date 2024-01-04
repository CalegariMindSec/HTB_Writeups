package org.hibernate.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.hibernate.usertype.CompositeUserType;

@java.lang.annotation.Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.PACKAGE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(CompositeTypeRegistrations.class)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/annotations/CompositeTypeRegistration.class */
public @interface CompositeTypeRegistration {
    Class<?> embeddableClass();

    Class<? extends CompositeUserType<?>> userType();
}