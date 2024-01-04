package jakarta.servlet.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-core-10.1.5.jar:jakarta/servlet/annotation/HandlesTypes.class */
public @interface HandlesTypes {
    Class<?>[] value();
}