package org.hibernate.resource.jdbc.spi;

import java.sql.Connection;

@Deprecated
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/resource/jdbc/spi/JdbcObserver.class */
public interface JdbcObserver {
    void jdbcConnectionAcquisitionStart();

    void jdbcConnectionAcquisitionEnd(Connection connection);

    void jdbcConnectionReleaseStart();

    void jdbcConnectionReleaseEnd();

    void jdbcPrepareStatementStart();

    void jdbcPrepareStatementEnd();

    void jdbcExecuteStatementStart();

    void jdbcExecuteStatementEnd();

    void jdbcExecuteBatchStart();

    void jdbcExecuteBatchEnd();

    default void jdbcReleaseRegistryResourcesStart() {
    }

    default void jdbcReleaseRegistryResourcesEnd() {
    }
}