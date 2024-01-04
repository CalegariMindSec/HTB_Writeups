package org.hibernate.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.hibernate.property.access.spi.PropertyAccessStrategy;
import org.hibernate.tuple.AttributeAccessorBinder;

@java.lang.annotation.Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@AttributeBinderType(binder = AttributeAccessorBinder.class)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/annotations/AttributeAccessor.class */
public @interface AttributeAccessor {
    @Deprecated(since = "6.0")
    String value() default "";

    Class<? extends PropertyAccessStrategy> strategy() default PropertyAccessStrategy.class;
}