package jakarta.transaction;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.transaction-api-2.0.1.jar:jakarta/transaction/UserTransaction.class */
public interface UserTransaction {
    void begin() throws NotSupportedException, SystemException;

    void commit() throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException;

    void rollback() throws IllegalStateException, SecurityException, SystemException;

    void setRollbackOnly() throws IllegalStateException, SystemException;

    int getStatus() throws SystemException;

    void setTransactionTimeout(int i) throws SystemException;
}