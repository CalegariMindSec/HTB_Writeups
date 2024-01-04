package org.glassfish.jaxb.runtime.v2.runtime.reflect;

import jakarta.xml.bind.JAXBException;
import org.xml.sax.SAXException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jaxb-runtime-4.0.1.jar:org/glassfish/jaxb/runtime/v2/runtime/reflect/ListIterator.class */
public interface ListIterator<E> {
    boolean hasNext();

    E next() throws SAXException, JAXBException;
}