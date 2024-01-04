package org.checkerframework.common.value.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.SubtypeOf;

@Target({})
@Documented
@Retention(RetentionPolicy.SOURCE)
@SubtypeOf({UnknownVal.class})
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/checker-qual-3.5.0.jar:org/checkerframework/common/value/qual/IntRangeFromPositive.class */
public @interface IntRangeFromPositive {
}