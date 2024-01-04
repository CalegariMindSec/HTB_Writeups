package com.sun.istack;

import org.xml.sax.Locator;
import org.xml.sax.SAXParseException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/istack-commons-runtime-4.1.1.jar:com/sun/istack/SAXParseException2.class */
public class SAXParseException2 extends SAXParseException {
    private static final long serialVersionUID = 1304853690034671001L;

    public SAXParseException2(String message, Locator locator) {
        super(message, locator);
    }

    public SAXParseException2(String message, Locator locator, Exception e) {
        super(message, locator, e);
    }

    public SAXParseException2(String message, String publicId, String systemId, int lineNumber, int columnNumber) {
        super(message, publicId, systemId, lineNumber, columnNumber);
    }

    public SAXParseException2(String message, String publicId, String systemId, int lineNumber, int columnNumber, Exception e) {
        super(message, publicId, systemId, lineNumber, columnNumber, e);
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return getException();
    }
}