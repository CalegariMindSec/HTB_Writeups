package org.hibernate.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/annotations/ListIndexBase.class */
public @interface ListIndexBase {
    int value() default 0;
}