package org.hibernate.annotations.common.annotationfactory;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-commons-annotations-6.0.2.Final.jar:org/hibernate/annotations/common/annotationfactory/AnnotationDescriptor.class */
public final class AnnotationDescriptor {
    private final Class<? extends Annotation> type;
    private Map<String, Object> elements;

    public AnnotationDescriptor(Class<? extends Annotation> annotationType) {
        this.type = annotationType;
    }

    public void setValue(String elementName, Object value) {
        if (this.elements == null) {
            this.elements = new HashMap(4);
        }
        this.elements.put(elementName, value);
    }

    public Object valueOf(String elementName) {
        if (this.elements == null) {
            return null;
        }
        return this.elements.get(elementName);
    }

    public boolean containsElement(String elementName) {
        if (this.elements == null) {
            return false;
        }
        return this.elements.containsKey(elementName);
    }

    public int numberOfElements() {
        if (this.elements == null) {
            return 0;
        }
        return this.elements.size();
    }

    public Class<? extends Annotation> type() {
        return this.type;
    }
}