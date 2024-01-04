package lombok;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
@Deprecated
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:lombok/Delegate.class */
public @interface Delegate {
    Class<?>[] types() default {};

    Class<?>[] excludes() default {};
}