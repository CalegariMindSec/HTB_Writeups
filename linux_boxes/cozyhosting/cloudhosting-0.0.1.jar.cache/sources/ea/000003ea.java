package com.fasterxml.jackson.databind.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jackson-databind-2.14.1.jar:com/fasterxml/jackson/databind/annotation/JsonNaming.class */
public @interface JsonNaming {
    Class<? extends PropertyNamingStrategy> value() default PropertyNamingStrategy.class;
}