package org.glassfish.jaxb.core.api;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jaxb-core-4.0.1.jar:org/glassfish/jaxb/core/api/ErrorListener.class */
public interface ErrorListener extends ErrorHandler {
    @Override // org.xml.sax.ErrorHandler
    void error(SAXParseException sAXParseException);

    @Override // org.xml.sax.ErrorHandler
    void fatalError(SAXParseException sAXParseException);

    @Override // org.xml.sax.ErrorHandler
    void warning(SAXParseException sAXParseException);

    void info(SAXParseException sAXParseException);
}