package jakarta.xml.bind.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.xml.bind-api-4.0.0.jar:jakarta/xml/bind/annotation/XmlElementRef.class */
public @interface XmlElementRef {
    Class<?> type() default DEFAULT.class;

    String namespace() default "";

    String name() default "##default";

    boolean required() default true;

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.xml.bind-api-4.0.0.jar:jakarta/xml/bind/annotation/XmlElementRef$DEFAULT.class */
    public static final class DEFAULT {
        private DEFAULT() {
        }
    }
}