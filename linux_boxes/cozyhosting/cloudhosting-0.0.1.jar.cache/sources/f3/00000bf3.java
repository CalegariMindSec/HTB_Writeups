package jakarta.xml.bind;

import org.xml.sax.ContentHandler;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.xml.bind-api-4.0.0.jar:jakarta/xml/bind/UnmarshallerHandler.class */
public interface UnmarshallerHandler extends ContentHandler {
    Object getResult() throws JAXBException, IllegalStateException;
}