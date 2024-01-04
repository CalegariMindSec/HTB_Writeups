package io.micrometer.common.lang;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.annotation.Nonnull;
import javax.annotation.meta.TypeQualifierNickname;

@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Nonnull
@TypeQualifierNickname
@Retention(RetentionPolicy.RUNTIME)
@Documented
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/micrometer-commons-1.10.3.jar:io/micrometer/common/lang/NonNull.class */
public @interface NonNull {
}