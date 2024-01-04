package jakarta.persistence;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Repeatable(SequenceGenerators.class)
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.persistence-api-3.1.0.jar:jakarta/persistence/SequenceGenerator.class */
public @interface SequenceGenerator {
    String name();

    String sequenceName() default "";

    String catalog() default "";

    String schema() default "";

    int initialValue() default 1;

    int allocationSize() default 50;
}