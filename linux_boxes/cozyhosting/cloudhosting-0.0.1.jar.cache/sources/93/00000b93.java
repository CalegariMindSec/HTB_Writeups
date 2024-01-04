package jakarta.transaction;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.transaction-api-2.0.1.jar:jakarta/transaction/TransactionalException.class */
public class TransactionalException extends RuntimeException {
    private static final long serialVersionUID = -8196645329560986417L;

    public TransactionalException(String s, Throwable throwable) {
        super(s, throwable);
    }
}