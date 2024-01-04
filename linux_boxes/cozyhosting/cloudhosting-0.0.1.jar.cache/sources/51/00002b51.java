package org.hibernate.annotations.common.reflection;

import java.lang.annotation.Annotation;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-commons-annotations-6.0.2.Final.jar:org/hibernate/annotations/common/reflection/XAnnotatedElement.class */
public interface XAnnotatedElement {
    <T extends Annotation> T getAnnotation(Class<T> cls);

    <T extends Annotation> boolean isAnnotationPresent(Class<T> cls);

    Annotation[] getAnnotations();

    boolean equals(Object obj);
}