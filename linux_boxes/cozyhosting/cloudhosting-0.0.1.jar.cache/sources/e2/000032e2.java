package org.hibernate.engine.jdbc.connections.spi;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/jdbc/connections/spi/JdbcConnectionAccess.class */
public interface JdbcConnectionAccess extends Serializable {
    Connection obtainConnection() throws SQLException;

    void releaseConnection(Connection connection) throws SQLException;

    boolean supportsAggressiveRelease();
}