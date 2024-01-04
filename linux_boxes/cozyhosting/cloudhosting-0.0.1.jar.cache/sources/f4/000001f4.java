package ch.qos.logback.core.recovery;

import java.io.IOException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/logback-core-1.4.5.jar:ch/qos/logback/core/recovery/RecoveryListener.class */
public interface RecoveryListener {
    void newFailure(IOException iOException);

    void recoveryOccured();
}