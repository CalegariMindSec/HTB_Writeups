package org.apache.tomcat.util.http.fileupload;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-core-10.1.5.jar:org/apache/tomcat/util/http/fileupload/RequestContext.class */
public interface RequestContext {
    String getCharacterEncoding();

    String getContentType();

    InputStream getInputStream() throws IOException;
}