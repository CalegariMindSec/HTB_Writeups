package org.postgresql;

import java.sql.SQLException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/PGResultSetMetaData.class */
public interface PGResultSetMetaData {
    String getBaseColumnName(int i) throws SQLException;

    String getBaseTableName(int i) throws SQLException;

    String getBaseSchemaName(int i) throws SQLException;

    int getFormat(int i) throws SQLException;
}