package org.postgresql.core;

import java.sql.SQLException;
import java.util.Iterator;
import org.postgresql.util.PGobject;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/core/TypeInfo.class */
public interface TypeInfo {
    void addCoreType(String str, Integer num, Integer num2, String str2, Integer num3);

    void addDataType(String str, Class<? extends PGobject> cls) throws SQLException;

    int getSQLType(int i) throws SQLException;

    int getSQLType(String str) throws SQLException;

    int getJavaArrayType(String str) throws SQLException;

    int getPGType(String str) throws SQLException;

    String getPGType(int i) throws SQLException;

    int getPGArrayElement(int i) throws SQLException;

    int getPGArrayType(String str) throws SQLException;

    char getArrayDelimiter(int i) throws SQLException;

    Iterator<String> getPGTypeNamesWithSQLTypes();

    Iterator<Integer> getPGTypeOidsWithSQLTypes();

    Class<? extends PGobject> getPGobject(String str);

    String getJavaClass(int i) throws SQLException;

    String getTypeForAlias(String str);

    int getPrecision(int i, int i2);

    int getScale(int i, int i2);

    boolean isCaseSensitive(int i);

    boolean isSigned(int i);

    int getDisplaySize(int i, int i2);

    int getMaximumPrecision(int i);

    boolean requiresQuoting(int i) throws SQLException;

    boolean requiresQuotingSqlType(int i) throws SQLException;

    int longOidToInt(long j) throws SQLException;

    long intOidToLong(int i);
}