package org.checkerframework.checker.nullness.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.MonotonicQualifier;
import org.checkerframework.framework.qual.SubtypeOf;

@Target({ElementType.TYPE_USE})
@MonotonicQualifier(NonNull.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@SubtypeOf({Nullable.class})
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/checker-qual-3.5.0.jar:org/checkerframework/checker/nullness/qual/MonotonicNonNull.class */
public @interface MonotonicNonNull {
}