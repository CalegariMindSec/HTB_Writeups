package jakarta.xml.bind;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.xml.bind-api-4.0.0.jar:jakarta/xml/bind/DataBindingException.class */
public class DataBindingException extends RuntimeException {
    private static final long serialVersionUID = 4743686626270704879L;

    public DataBindingException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataBindingException(Throwable cause) {
        super(cause);
    }
}