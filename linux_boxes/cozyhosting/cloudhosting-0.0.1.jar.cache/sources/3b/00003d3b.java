package org.hibernate.resource.transaction.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/resource/transaction/spi/TransactionStatus.class */
public enum TransactionStatus {
    NOT_ACTIVE,
    ACTIVE,
    COMMITTED,
    ROLLED_BACK,
    MARKED_ROLLBACK,
    FAILED_COMMIT,
    FAILED_ROLLBACK,
    COMMITTING,
    ROLLING_BACK;

    public boolean isOneOf(TransactionStatus... statuses) {
        for (TransactionStatus status : statuses) {
            if (this == status) {
                return true;
            }
        }
        return false;
    }

    public boolean isNotOneOf(TransactionStatus... statuses) {
        return !isOneOf(statuses);
    }

    public boolean canRollback() {
        return isOneOf(ACTIVE, FAILED_COMMIT, MARKED_ROLLBACK);
    }
}