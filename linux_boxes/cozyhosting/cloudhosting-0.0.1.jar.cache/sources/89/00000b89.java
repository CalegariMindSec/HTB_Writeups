package jakarta.transaction;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.transaction-api-2.0.1.jar:jakarta/transaction/Synchronization.class */
public interface Synchronization {
    void beforeCompletion();

    void afterCompletion(int i);
}