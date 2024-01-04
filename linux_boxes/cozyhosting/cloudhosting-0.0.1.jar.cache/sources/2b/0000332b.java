package org.hibernate.engine.jdbc.spi;

import java.sql.Connection;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/jdbc/spi/ConnectionObserver.class */
public interface ConnectionObserver {
    void physicalConnectionObtained(Connection connection);

    void physicalConnectionReleased();

    void logicalConnectionClosed();

    void statementPrepared();
}