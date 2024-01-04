package net.bytebuddy.utility.nullability;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.annotation.Nonnull;
import javax.annotation.meta.TypeQualifierDefault;
import javax.annotation.meta.TypeQualifierNickname;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Nonnull
@TypeQualifierNickname
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/utility/nullability/NeverNull.class */
public @interface NeverNull {

    @Target({ElementType.PACKAGE})
    @TypeQualifierDefault({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    @Nonnull
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/utility/nullability/NeverNull$ByDefault.class */
    public @interface ByDefault {
    }
}