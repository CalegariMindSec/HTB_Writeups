package org.hibernate.annotations;

import jakarta.persistence.AttributeConverter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@java.lang.annotation.Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.PACKAGE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ConverterRegistrations.class)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/annotations/ConverterRegistration.class */
public @interface ConverterRegistration {
    Class<? extends AttributeConverter<?, ?>> converter();

    Class<?> domainType() default void.class;

    boolean autoApply() default true;
}