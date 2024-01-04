package org.hibernate.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.hibernate.tuple.TenantIdBinder;
import org.hibernate.tuple.TenantIdGeneration;

@ValueGenerationType(generatedBy = TenantIdGeneration.class)
@java.lang.annotation.Target({ElementType.METHOD, ElementType.FIELD})
@AttributeBinderType(binder = TenantIdBinder.class)
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/annotations/TenantId.class */
public @interface TenantId {
}