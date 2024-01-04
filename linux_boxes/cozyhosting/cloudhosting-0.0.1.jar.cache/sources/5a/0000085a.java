package io.micrometer.core.instrument.binder.jersey.server;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/micrometer-core-1.10.3.jar:io/micrometer/core/instrument/binder/jersey/server/AnnotationFinder.class */
public interface AnnotationFinder {
    public static final AnnotationFinder DEFAULT = new AnnotationFinder() { // from class: io.micrometer.core.instrument.binder.jersey.server.AnnotationFinder.1
    };

    default <A extends Annotation> A findAnnotation(AnnotatedElement annotatedElement, Class<A> annotationType) {
        Annotation[] anns = annotatedElement.getDeclaredAnnotations();
        for (Annotation annotation : anns) {
            A a = (A) annotation;
            if (a.annotationType() == annotationType) {
                return a;
            }
        }
        return null;
    }
}