package org.checkerframework.checker.signedness.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/checker-qual-3.5.0.jar:org/checkerframework/checker/signedness/qual/SignedPositive.class */
public @interface SignedPositive {
}