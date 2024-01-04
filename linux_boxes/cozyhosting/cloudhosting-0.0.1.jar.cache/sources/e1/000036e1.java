package org.hibernate.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/jdbc/ReturningWork.class */
public interface ReturningWork<T> {
    T execute(Connection connection) throws SQLException;
}