package org.checkerframework.checker.tainting.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.DefaultFor;
import org.checkerframework.framework.qual.LiteralKind;
import org.checkerframework.framework.qual.QualifierForLiterals;
import org.checkerframework.framework.qual.SubtypeOf;
import org.checkerframework.framework.qual.TypeUseLocation;

@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@QualifierForLiterals({LiteralKind.STRING})
@DefaultFor({TypeUseLocation.LOWER_BOUND})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@SubtypeOf({Tainted.class})
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/checker-qual-3.5.0.jar:org/checkerframework/checker/tainting/qual/Untainted.class */
public @interface Untainted {
}