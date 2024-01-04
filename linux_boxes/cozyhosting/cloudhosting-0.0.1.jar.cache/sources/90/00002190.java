package org.apache.tomcat.util.threads;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-core-10.1.5.jar:org/apache/tomcat/util/threads/StopPooledThreadException.class */
public class StopPooledThreadException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public StopPooledThreadException(String msg) {
        super(msg, null, false, false);
    }
}