package jakarta.websocket;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-websocket-10.1.5.jar:jakarta/websocket/EncodeException.class */
public class EncodeException extends Exception {
    private static final long serialVersionUID = 1;
    private Object object;

    public EncodeException(Object object, String message) {
        super(message);
        this.object = object;
    }

    public EncodeException(Object object, String message, Throwable cause) {
        super(message, cause);
        this.object = object;
    }

    public Object getObject() {
        return this.object;
    }
}