package org.glassfish.jaxb.core.v2;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jaxb-core-4.0.1.jar:org/glassfish/jaxb/core/v2/Messages.class */
public enum Messages {
    ILLEGAL_ENTRY,
    ERROR_LOADING_CLASS,
    INVALID_PROPERTY_VALUE,
    UNSUPPORTED_PROPERTY,
    BROKEN_CONTEXTPATH,
    NO_DEFAULT_CONSTRUCTOR_IN_INNER_CLASS,
    INVALID_TYPE_IN_MAP,
    INVALID_JAXP_IMPLEMENTATION,
    JAXP_SUPPORTED_PROPERTY,
    JAXP_UNSUPPORTED_PROPERTY,
    JAXP_XML_SECURITY_DISABLED,
    JAXP_EXTERNAL_ACCESS_CONFIGURED;
    
    private static final ResourceBundle rb = ResourceBundle.getBundle(Messages.class.getName());

    @Override // java.lang.Enum
    public String toString() {
        return format(new Object[0]);
    }

    public String format(Object... args) {
        return MessageFormat.format(rb.getString(name()), args);
    }
}