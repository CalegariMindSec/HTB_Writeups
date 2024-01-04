package jakarta.transaction;

import java.rmi.RemoteException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.transaction-api-2.0.1.jar:jakarta/transaction/TransactionRequiredException.class */
public class TransactionRequiredException extends RemoteException {
    private static final long serialVersionUID = -1898806419937446439L;

    public TransactionRequiredException() {
    }

    public TransactionRequiredException(String msg) {
        super(msg);
    }
}