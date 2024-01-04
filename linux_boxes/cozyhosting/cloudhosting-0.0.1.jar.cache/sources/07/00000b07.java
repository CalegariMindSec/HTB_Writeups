package jakarta.persistence.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.persistence-api-3.1.0.jar:jakarta/persistence/spi/TransformerException.class */
public class TransformerException extends Exception {
    private static final long serialVersionUID = 7484555485977030491L;

    public TransformerException() {
    }

    public TransformerException(String message) {
        super(message);
    }

    public TransformerException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransformerException(Throwable cause) {
        super(cause);
    }
}