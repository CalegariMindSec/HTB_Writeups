package org.hibernate.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.hibernate.HibernateException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/jdbc/Expectation.class */
public interface Expectation {
    void verifyOutcome(int i, PreparedStatement preparedStatement, int i2, String str) throws SQLException, HibernateException;

    int prepare(PreparedStatement preparedStatement) throws SQLException, HibernateException;

    boolean canBeBatched();
}