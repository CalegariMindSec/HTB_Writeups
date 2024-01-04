package jakarta.xml.bind.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.xml.bind-api-4.0.0.jar:jakarta/xml/bind/annotation/XmlType.class */
public @interface XmlType {
    String name() default "##default";

    String[] propOrder() default {""};

    String namespace() default "##default";

    Class<?> factoryClass() default DEFAULT.class;

    String factoryMethod() default "";

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.xml.bind-api-4.0.0.jar:jakarta/xml/bind/annotation/XmlType$DEFAULT.class */
    public static final class DEFAULT {
        private DEFAULT() {
        }
    }
}