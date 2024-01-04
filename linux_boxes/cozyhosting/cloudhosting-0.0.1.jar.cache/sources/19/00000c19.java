package jakarta.xml.bind.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PACKAGE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.xml.bind-api-4.0.0.jar:jakarta/xml/bind/annotation/XmlSchemaType.class */
public @interface XmlSchemaType {
    String name();

    String namespace() default "http://www.w3.org/2001/XMLSchema";

    Class<?> type() default DEFAULT.class;

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.xml.bind-api-4.0.0.jar:jakarta/xml/bind/annotation/XmlSchemaType$DEFAULT.class */
    public static final class DEFAULT {
        private DEFAULT() {
        }
    }
}