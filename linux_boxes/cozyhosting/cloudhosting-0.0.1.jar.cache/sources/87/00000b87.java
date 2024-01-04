package jakarta.transaction;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.transaction-api-2.0.1.jar:jakarta/transaction/RollbackException.class */
public class RollbackException extends Exception {
    private static final long serialVersionUID = 4151607774785285395L;

    public RollbackException() {
    }

    public RollbackException(String msg) {
        super(msg);
    }
}