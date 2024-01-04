package org.hibernate.engine.jdbc.connections.internal;

import java.sql.Connection;
import java.sql.SQLException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/jdbc/connections/internal/ConnectionValidator.class */
public interface ConnectionValidator {
    public static final ConnectionValidator ALWAYS_VALID = connection -> {
        return true;
    };

    boolean isValid(Connection connection) throws SQLException;
}