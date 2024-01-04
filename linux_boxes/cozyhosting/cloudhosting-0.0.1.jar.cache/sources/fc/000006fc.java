package com.sun.istack;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/istack-commons-runtime-4.1.1.jar:com/sun/istack/FragmentContentHandler.class */
public class FragmentContentHandler extends XMLFilterImpl {
    public FragmentContentHandler() {
    }

    public FragmentContentHandler(XMLReader parent) {
        super(parent);
    }

    public FragmentContentHandler(ContentHandler handler) {
        setContentHandler(handler);
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
    }
}