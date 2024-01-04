package org.glassfish.jaxb.core.v2.model.annotation;

import java.lang.annotation.Annotation;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jaxb-core-4.0.1.jar:org/glassfish/jaxb/core/v2/model/annotation/AnnotationSource.class */
public interface AnnotationSource {
    <A extends Annotation> A readAnnotation(Class<A> cls);

    boolean hasAnnotation(Class<? extends Annotation> cls);
}