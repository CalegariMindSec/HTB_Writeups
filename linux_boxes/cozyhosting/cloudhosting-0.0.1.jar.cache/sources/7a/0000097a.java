package io.micrometer.core.lang;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.annotation.Nonnull;
import javax.annotation.meta.TypeQualifierDefault;

@Target({ElementType.PACKAGE, ElementType.TYPE})
@TypeQualifierDefault({ElementType.METHOD, ElementType.PARAMETER})
@Nonnull
@Deprecated
@Retention(RetentionPolicy.RUNTIME)
@Documented
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/micrometer-core-1.10.3.jar:io/micrometer/core/lang/NonNullApi.class */
public @interface NonNullApi {
}