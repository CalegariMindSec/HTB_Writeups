package org.checkerframework.framework.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE, ElementType.PACKAGE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@SubtypeOf({})
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/checker-qual-3.5.0.jar:org/checkerframework/framework/qual/FromByteCode.class */
public @interface FromByteCode {
}