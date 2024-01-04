package jakarta.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Deprecated
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.annotation-api-2.1.1.jar:jakarta/annotation/ManagedBean.class */
public @interface ManagedBean {
    String value() default "";
}