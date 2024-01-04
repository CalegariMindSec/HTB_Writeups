package org.apache.tomcat.websocket;

import java.io.IOException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-websocket-10.1.5.jar:org/apache/tomcat/websocket/ReadBufferOverflowException.class */
public class ReadBufferOverflowException extends IOException {
    private static final long serialVersionUID = 1;
    private final int minBufferSize;

    public ReadBufferOverflowException(int minBufferSize) {
        this.minBufferSize = minBufferSize;
    }

    public int getMinBufferSize() {
        return this.minBufferSize;
    }
}