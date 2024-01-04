package jakarta.xml.bind.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.xml.bind-api-4.0.0.jar:jakarta/xml/bind/annotation/XmlElementDecl.class */
public @interface XmlElementDecl {
    Class<?> scope() default GLOBAL.class;

    String namespace() default "##default";

    String name();

    String substitutionHeadNamespace() default "##default";

    String substitutionHeadName() default "";

    String defaultValue() default "��";

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.xml.bind-api-4.0.0.jar:jakarta/xml/bind/annotation/XmlElementDecl$GLOBAL.class */
    public static final class GLOBAL {
        private GLOBAL() {
        }
    }
}