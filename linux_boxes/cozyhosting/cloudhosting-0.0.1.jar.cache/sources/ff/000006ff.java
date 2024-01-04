package com.sun.istack;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
@Documented
@Retention(RetentionPolicy.CLASS)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/istack-commons-runtime-4.1.1.jar:com/sun/istack/Nullable.class */
public @interface Nullable {
}