package org.hibernate.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.hibernate.tuple.AnnotationValueGeneration;

@java.lang.annotation.Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/annotations/ValueGenerationType.class */
public @interface ValueGenerationType {
    Class<? extends AnnotationValueGeneration<?>> generatedBy();
}