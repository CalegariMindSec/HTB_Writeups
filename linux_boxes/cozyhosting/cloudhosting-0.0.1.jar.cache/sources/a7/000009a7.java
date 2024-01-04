package io.micrometer.observation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/micrometer-observation-1.10.3.jar:io/micrometer/observation/annotation/Observed.class */
public @interface Observed {
    String name() default "";

    String contextualName() default "";

    String[] lowCardinalityKeyValues() default {};
}