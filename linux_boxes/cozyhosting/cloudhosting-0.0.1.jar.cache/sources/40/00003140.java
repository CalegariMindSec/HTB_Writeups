package org.hibernate.dialect;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/dialect/ColumnAliasExtractor.class */
public interface ColumnAliasExtractor {
    public static final ColumnAliasExtractor COLUMN_LABEL_EXTRACTOR = (v0, v1) -> {
        return v0.getColumnLabel(v1);
    };
    public static final ColumnAliasExtractor COLUMN_NAME_EXTRACTOR = (v0, v1) -> {
        return v0.getColumnName(v1);
    };

    String extractColumnAlias(ResultSetMetaData resultSetMetaData, int i) throws SQLException;
}