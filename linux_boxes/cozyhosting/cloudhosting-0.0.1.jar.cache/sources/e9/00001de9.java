package org.apache.coyote.http11;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-core-10.1.5.jar:org/apache/coyote/http11/HeadersTooLargeException.class */
public class HeadersTooLargeException extends IllegalStateException {
    private static final long serialVersionUID = 1;

    public HeadersTooLargeException() {
    }

    public HeadersTooLargeException(String message, Throwable cause) {
        super(message, cause);
    }

    public HeadersTooLargeException(String s) {
        super(s);
    }

    public HeadersTooLargeException(Throwable cause) {
        super(cause);
    }
}