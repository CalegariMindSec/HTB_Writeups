package org.hibernate.engine.jdbc.spi;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/jdbc/spi/ResultSetReturn.class */
public interface ResultSetReturn {
    ResultSet extract(PreparedStatement preparedStatement);

    ResultSet extract(CallableStatement callableStatement);

    ResultSet extract(Statement statement, String str);

    ResultSet execute(PreparedStatement preparedStatement);

    ResultSet execute(Statement statement, String str);

    int executeUpdate(PreparedStatement preparedStatement);

    int executeUpdate(Statement statement, String str);
}