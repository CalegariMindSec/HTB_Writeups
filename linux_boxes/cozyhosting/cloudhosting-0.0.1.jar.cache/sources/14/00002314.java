package org.aspectj.internal.lang.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/internal/lang/annotation/ajcITD.class */
public @interface ajcITD {
    int modifiers();

    String targetType();

    String name();
}