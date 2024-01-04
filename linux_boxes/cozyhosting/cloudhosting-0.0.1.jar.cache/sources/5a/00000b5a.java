package jakarta.servlet.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-core-10.1.5.jar:jakarta/servlet/annotation/MultipartConfig.class */
public @interface MultipartConfig {
    String location() default "";

    long maxFileSize() default -1;

    long maxRequestSize() default -1;

    int fileSizeThreshold() default 0;
}