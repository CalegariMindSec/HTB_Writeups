package jakarta.transaction;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.transaction-api-2.0.1.jar:jakarta/transaction/HeuristicCommitException.class */
public class HeuristicCommitException extends Exception {
    private static final long serialVersionUID = -3977609782149921760L;

    public HeuristicCommitException() {
    }

    public HeuristicCommitException(String msg) {
        super(msg);
    }
}