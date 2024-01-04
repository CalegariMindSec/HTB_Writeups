package jakarta.transaction;

import java.rmi.RemoteException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.transaction-api-2.0.1.jar:jakarta/transaction/TransactionRolledbackException.class */
public class TransactionRolledbackException extends RemoteException {
    private static final long serialVersionUID = -3142798139623020577L;

    public TransactionRolledbackException() {
    }

    public TransactionRolledbackException(String msg) {
        super(msg);
    }
}