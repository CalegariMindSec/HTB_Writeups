package org.postgresql.jdbc;

import java.sql.ResultSet;
import org.checkerframework.dataflow.qual.Pure;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/jdbc/ResultWrapper.class */
public class ResultWrapper {
    private final ResultSet rs;
    private final long updateCount;
    private final long insertOID;
    private ResultWrapper next;

    public ResultWrapper(ResultSet rs) {
        this.rs = rs;
        this.updateCount = -1L;
        this.insertOID = -1L;
    }

    public ResultWrapper(long updateCount, long insertOID) {
        this.rs = null;
        this.updateCount = updateCount;
        this.insertOID = insertOID;
    }

    @Pure
    public ResultSet getResultSet() {
        return this.rs;
    }

    public long getUpdateCount() {
        return this.updateCount;
    }

    public long getInsertOID() {
        return this.insertOID;
    }

    public ResultWrapper getNext() {
        return this.next;
    }

    public void append(ResultWrapper newResult) {
        ResultWrapper resultWrapper = this;
        while (true) {
            ResultWrapper tail = resultWrapper;
            if (tail.next != null) {
                resultWrapper = tail.next;
            } else {
                tail.next = newResult;
                return;
            }
        }
    }
}