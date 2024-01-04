package org.aopalliance.aop;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/spring-aop-6.0.4.jar:org/aopalliance/aop/AspectException.class */
public class AspectException extends RuntimeException {
    public AspectException(String message) {
        super(message);
    }

    public AspectException(String message, Throwable cause) {
        super(message, cause);
    }
}