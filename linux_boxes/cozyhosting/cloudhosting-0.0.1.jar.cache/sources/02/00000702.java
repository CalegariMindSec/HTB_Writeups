package com.sun.istack;

import org.xml.sax.SAXException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/istack-commons-runtime-4.1.1.jar:com/sun/istack/SAXException2.class */
public class SAXException2 extends SAXException {
    private static final long serialVersionUID = -707119042406163844L;

    public SAXException2(String message) {
        super(message);
    }

    public SAXException2(Exception e) {
        super(e);
    }

    public SAXException2(String message, Exception e) {
        super(message, e);
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return getException();
    }
}