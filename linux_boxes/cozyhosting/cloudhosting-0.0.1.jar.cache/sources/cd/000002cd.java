package com.fasterxml.jackson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jackson-annotations-2.14.1.jar:com/fasterxml/jackson/annotation/JsonIdentityInfo.class */
public @interface JsonIdentityInfo {
    String property() default "@id";

    Class<? extends ObjectIdGenerator<?>> generator();

    Class<? extends ObjectIdResolver> resolver() default SimpleObjectIdResolver.class;

    Class<?> scope() default Object.class;
}