package jakarta.inject;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.inject-api-2.0.0.jar:jakarta/inject/Named.class */
public @interface Named {
    String value() default "";
}