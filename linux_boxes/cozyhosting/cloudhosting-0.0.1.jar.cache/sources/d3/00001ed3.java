package org.apache.logging.log4j;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/log4j-api-2.19.0.jar:org/apache/logging/log4j/LoggingException.class */
public class LoggingException extends RuntimeException {
    private static final long serialVersionUID = 6366395965071580537L;

    public LoggingException(final String message) {
        super(message);
    }

    public LoggingException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public LoggingException(final Throwable cause) {
        super(cause);
    }
}