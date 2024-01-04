package jakarta.persistence;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.persistence-api-3.1.0.jar:jakarta/persistence/NamedAttributeNode.class */
public @interface NamedAttributeNode {
    String value();

    String subgraph() default "";

    String keySubgraph() default "";
}