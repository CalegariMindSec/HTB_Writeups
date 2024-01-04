package org.postgresql.jdbc;

import java.sql.SQLWarning;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/jdbc/PSQLWarningWrapper.class */
class PSQLWarningWrapper {
    private final SQLWarning firstWarning;
    private SQLWarning lastWarning;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PSQLWarningWrapper(SQLWarning warning) {
        this.firstWarning = warning;
        this.lastWarning = warning;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addWarning(SQLWarning sqlWarning) {
        this.lastWarning.setNextWarning(sqlWarning);
        this.lastWarning = sqlWarning;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SQLWarning getFirstWarning() {
        return this.firstWarning;
    }
}