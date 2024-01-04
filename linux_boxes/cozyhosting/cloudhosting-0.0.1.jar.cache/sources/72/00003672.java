package org.hibernate.internal.util;

import java.sql.SQLException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/JdbcExceptionHelper.class */
public final class JdbcExceptionHelper {
    private JdbcExceptionHelper() {
    }

    public static int extractErrorCode(SQLException sqlException) {
        int errorCode = sqlException.getErrorCode();
        SQLException nextException = sqlException.getNextException();
        while (true) {
            SQLException nested = nextException;
            if (errorCode != 0 || nested == null) {
                break;
            }
            errorCode = nested.getErrorCode();
            nextException = nested.getNextException();
        }
        return errorCode;
    }

    public static String extractSqlState(SQLException sqlException) {
        String sqlState = sqlException.getSQLState();
        SQLException nextException = sqlException.getNextException();
        while (true) {
            SQLException nested = nextException;
            if (sqlState != null || nested == null) {
                break;
            }
            sqlState = nested.getSQLState();
            nextException = nested.getNextException();
        }
        return sqlState;
    }

    public static String extractSqlStateClassCode(SQLException sqlException) {
        return determineSqlStateClassCode(extractSqlState(sqlException));
    }

    public static String determineSqlStateClassCode(String sqlState) {
        if (sqlState == null || sqlState.length() < 2) {
            return sqlState;
        }
        return sqlState.substring(0, 2);
    }
}