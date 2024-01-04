package org.postgresql.copy;

import java.sql.SQLException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/copy/CopyOperation.class */
public interface CopyOperation {
    int getFieldCount();

    int getFormat();

    int getFieldFormat(int i);

    boolean isActive();

    void cancelCopy() throws SQLException;

    long getHandledRowCount();
}