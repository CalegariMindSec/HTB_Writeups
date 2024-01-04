package jakarta.xml.bind;

import java.net.URL;
import org.w3c.dom.Node;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.xml.bind-api-4.0.0.jar:jakarta/xml/bind/ValidationEventLocator.class */
public interface ValidationEventLocator {
    URL getURL();

    int getOffset();

    int getLineNumber();

    int getColumnNumber();

    Object getObject();

    Node getNode();
}