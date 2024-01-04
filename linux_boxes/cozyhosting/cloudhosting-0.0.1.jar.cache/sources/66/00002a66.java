package org.hibernate;

import java.io.Serializable;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/SessionEventListener.class */
public interface SessionEventListener extends Serializable {
    void transactionCompletion(boolean z);

    void jdbcConnectionAcquisitionStart();

    void jdbcConnectionAcquisitionEnd();

    void jdbcConnectionReleaseStart();

    void jdbcConnectionReleaseEnd();

    void jdbcPrepareStatementStart();

    void jdbcPrepareStatementEnd();

    void jdbcExecuteStatementStart();

    void jdbcExecuteStatementEnd();

    void jdbcExecuteBatchStart();

    void jdbcExecuteBatchEnd();

    void cachePutStart();

    void cachePutEnd();

    void cacheGetStart();

    void cacheGetEnd(boolean z);

    void flushStart();

    void flushEnd(int i, int i2);

    void partialFlushStart();

    void partialFlushEnd(int i, int i2);

    void dirtyCalculationStart();

    void dirtyCalculationEnd(boolean z);

    void end();
}