package org.hibernate.internal.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/log/SubSystemLogging.class */
public @interface SubSystemLogging {
    public static final String BASE = "org.hibernate.orm";

    String name();

    String description();

    boolean mixed() default false;
}