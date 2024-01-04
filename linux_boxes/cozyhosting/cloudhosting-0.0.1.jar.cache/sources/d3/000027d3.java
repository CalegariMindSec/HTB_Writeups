package org.glassfish.jaxb.core.v2.model.core;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jaxb-core-4.0.1.jar:org/glassfish/jaxb/core/v2/model/core/PropertyKind.class */
public enum PropertyKind {
    VALUE(true, false, Integer.MAX_VALUE),
    ATTRIBUTE(false, false, Integer.MAX_VALUE),
    ELEMENT(true, true, 0),
    REFERENCE(false, true, 1),
    MAP(false, true, 2);
    
    public final boolean canHaveXmlMimeType;
    public final boolean isOrdered;
    public final int propertyIndex;

    PropertyKind(boolean canHaveExpectedContentType, boolean isOrdered, int propertyIndex) {
        this.canHaveXmlMimeType = canHaveExpectedContentType;
        this.isOrdered = isOrdered;
        this.propertyIndex = propertyIndex;
    }
}