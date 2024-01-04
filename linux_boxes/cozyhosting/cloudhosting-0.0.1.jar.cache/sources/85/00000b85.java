package jakarta.transaction;

import java.rmi.RemoteException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.transaction-api-2.0.1.jar:jakarta/transaction/InvalidTransactionException.class */
public class InvalidTransactionException extends RemoteException {
    private static final long serialVersionUID = 3597320220337691496L;

    public InvalidTransactionException() {
    }

    public InvalidTransactionException(String msg) {
        super(msg);
    }
}