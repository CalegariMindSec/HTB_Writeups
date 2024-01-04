package org.hibernate.engine.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/jdbc/LobCreationContext.class */
public interface LobCreationContext {

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/jdbc/LobCreationContext$Callback.class */
    public interface Callback<T> {
        T executeOnConnection(Connection connection) throws SQLException;
    }

    <T> T execute(Callback<T> callback);
}