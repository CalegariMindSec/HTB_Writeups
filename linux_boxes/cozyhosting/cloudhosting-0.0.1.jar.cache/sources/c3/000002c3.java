package com.fasterxml.jackson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jackson-annotations-2.14.1.jar:com/fasterxml/jackson/annotation/JsonCreator.class */
public @interface JsonCreator {

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jackson-annotations-2.14.1.jar:com/fasterxml/jackson/annotation/JsonCreator$Mode.class */
    public enum Mode {
        DEFAULT,
        DELEGATING,
        PROPERTIES,
        DISABLED
    }

    Mode mode() default Mode.DEFAULT;
}