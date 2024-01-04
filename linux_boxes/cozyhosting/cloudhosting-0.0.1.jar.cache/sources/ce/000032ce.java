package org.hibernate.engine.jdbc.connections.internal;

import java.sql.Connection;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/jdbc/connections/internal/ConnectionCreator.class */
interface ConnectionCreator {
    String getUrl();

    Connection createConnection();
}