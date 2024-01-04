package jakarta.xml.bind.annotation.adapters;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PACKAGE, ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.xml.bind-api-4.0.0.jar:jakarta/xml/bind/annotation/adapters/XmlJavaTypeAdapter.class */
public @interface XmlJavaTypeAdapter {
    Class<? extends XmlAdapter> value();

    Class<?> type() default DEFAULT.class;

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.xml.bind-api-4.0.0.jar:jakarta/xml/bind/annotation/adapters/XmlJavaTypeAdapter$DEFAULT.class */
    public static final class DEFAULT {
        private DEFAULT() {
        }
    }
}