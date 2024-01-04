package org.postgresql.jdbc;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/jdbc/StatementCancelState.class */
enum StatementCancelState {
    IDLE,
    IN_QUERY,
    CANCELING,
    CANCELLED
}