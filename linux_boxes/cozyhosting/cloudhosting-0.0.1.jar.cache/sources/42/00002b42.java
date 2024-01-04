package org.hibernate.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@java.lang.annotation.Target({ElementType.FIELD, ElementType.METHOD})
@IdGeneratorType(org.hibernate.id.uuid.UuidGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/annotations/UuidGenerator.class */
public @interface UuidGenerator {

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/annotations/UuidGenerator$Style.class */
    public enum Style {
        AUTO,
        RANDOM,
        TIME
    }

    Style style() default Style.AUTO;
}