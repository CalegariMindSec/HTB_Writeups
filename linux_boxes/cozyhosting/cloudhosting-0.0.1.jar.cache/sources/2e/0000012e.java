package ch.qos.logback.core.joran.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/logback-core-1.4.5.jar:ch/qos/logback/core/joran/spi/ActionException.class */
public class ActionException extends Exception {
    private static final long serialVersionUID = 2743349809995319806L;

    public ActionException() {
    }

    public ActionException(String msg) {
        super(msg);
    }

    public ActionException(Throwable rootCause) {
        super(rootCause);
    }
}