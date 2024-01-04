package jakarta.transaction;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.transaction-api-2.0.1.jar:jakarta/transaction/HeuristicRollbackException.class */
public class HeuristicRollbackException extends Exception {
    private static final long serialVersionUID = -3483618944556408897L;

    public HeuristicRollbackException() {
    }

    public HeuristicRollbackException(String msg) {
        super(msg);
    }
}