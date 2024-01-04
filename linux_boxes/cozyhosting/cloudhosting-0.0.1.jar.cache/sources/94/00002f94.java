package org.hibernate.bytecode.enhance.spi;

import java.lang.annotation.Annotation;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/bytecode/enhance/spi/UnloadedField.class */
public interface UnloadedField {
    boolean hasAnnotation(Class<? extends Annotation> cls);
}