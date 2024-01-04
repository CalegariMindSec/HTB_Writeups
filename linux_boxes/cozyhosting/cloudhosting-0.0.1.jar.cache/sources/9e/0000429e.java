package org.postgresql;

import java.sql.SQLException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/PGStatement.class */
public interface PGStatement {
    public static final long DATE_POSITIVE_INFINITY = 9223372036825200000L;
    public static final long DATE_NEGATIVE_INFINITY = -9223372036832400000L;
    public static final long DATE_POSITIVE_SMALLER_INFINITY = 185543533774800000L;
    public static final long DATE_NEGATIVE_SMALLER_INFINITY = -185543533774800000L;

    long getLastOID() throws SQLException;

    @Deprecated
    void setUseServerPrepare(boolean z) throws SQLException;

    boolean isUseServerPrepare();

    void setPrepareThreshold(int i) throws SQLException;

    int getPrepareThreshold();

    void setAdaptiveFetch(boolean z);

    boolean getAdaptiveFetch();
}