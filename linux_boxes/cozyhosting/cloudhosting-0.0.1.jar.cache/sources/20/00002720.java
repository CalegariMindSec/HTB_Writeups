package org.checkerframework.checker.units.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.SubtypeOf;

@Target({})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@SubtypeOf({UnknownUnits.class})
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/checker-qual-3.5.0.jar:org/checkerframework/checker/units/qual/MixedUnits.class */
public @interface MixedUnits {
}