package org.antlr.v4.runtime.misc;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
@Deprecated
@Documented
@Retention(RetentionPolicy.CLASS)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/antlr4-runtime-4.10.1.jar:org/antlr/v4/runtime/misc/NotNull.class */
public @interface NotNull {
}