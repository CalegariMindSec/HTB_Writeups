package org.glassfish.jaxb.runtime.v2.runtime;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jaxb-runtime-4.0.1.jar:org/glassfish/jaxb/runtime/v2/runtime/Messages.class */
enum Messages {
    ILLEGAL_PARAMETER,
    UNABLE_TO_FIND_CONVERSION_METHOD,
    MISSING_ID,
    UNRECOGNIZED_ELEMENT_NAME,
    TYPE_MISMATCH,
    MISSING_OBJECT,
    NOT_IDENTIFIABLE,
    DANGLING_IDREF,
    NULL_OUTPUT_RESOLVER,
    UNABLE_TO_MARSHAL_NON_ELEMENT,
    UNABLE_TO_MARSHAL_UNBOUND_CLASS,
    UNSUPPORTED_PROPERTY,
    NULL_PROPERTY_NAME,
    MUST_BE_X,
    NOT_MARSHALLABLE,
    UNSUPPORTED_RESULT,
    UNSUPPORTED_ENCODING,
    SUBSTITUTED_BY_ANONYMOUS_TYPE,
    CYCLE_IN_MARSHALLER,
    UNABLE_TO_DISCOVER_EVENTHANDLER,
    ELEMENT_NEEDED_BUT_FOUND_DOCUMENT,
    UNKNOWN_CLASS,
    FAILED_TO_GENERATE_SCHEMA,
    ERROR_PROCESSING_SCHEMA,
    ILLEGAL_CONTENT;
    
    private static final ResourceBundle rb = ResourceBundle.getBundle(Messages.class.getName());

    @Override // java.lang.Enum
    public String toString() {
        return format(new Object[0]);
    }

    public String format(Object... args) {
        return MessageFormat.format(rb.getString(name()), args);
    }
}