package org.glassfish.jaxb.core.v2.model.core;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jaxb-core-4.0.1.jar:org/glassfish/jaxb/core/v2/model/core/WildcardMode.class */
public enum WildcardMode {
    STRICT(false, true),
    SKIP(true, false),
    LAX(true, true);
    
    public final boolean allowDom;
    public final boolean allowTypedObject;

    WildcardMode(boolean allowDom, boolean allowTypedObject) {
        this.allowDom = allowDom;
        this.allowTypedObject = allowTypedObject;
    }
}