package org.hibernate.sql.exec.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/sql/exec/spi/StatementOptions.class */
public class StatementOptions {
    public static final StatementOptions NONE = new StatementOptions(-1, -1, -1, -1);
    private final Integer firstRow;
    private final Integer maxRows;
    private final Integer timeoutInMilliseconds;
    private final Integer fetchSize;

    public StatementOptions(Integer firstRow, Integer maxRows, Integer timeoutInMilliseconds, Integer fetchSize) {
        this.firstRow = firstRow;
        this.maxRows = maxRows;
        this.timeoutInMilliseconds = timeoutInMilliseconds;
        this.fetchSize = fetchSize;
    }

    public boolean hasLimit() {
        return (this.firstRow != null && this.firstRow.intValue() > 0) || (this.maxRows != null && this.maxRows.intValue() > 0);
    }

    public Integer getFirstRow() {
        return this.firstRow;
    }

    public Integer getMaxRows() {
        return this.maxRows;
    }

    public boolean hasTimeout() {
        return this.timeoutInMilliseconds != null && this.timeoutInMilliseconds.intValue() > 0;
    }

    public Integer getTimeoutInMilliseconds() {
        return this.timeoutInMilliseconds;
    }

    public boolean hasFetchSize() {
        return this.fetchSize != null && this.fetchSize.intValue() > 0;
    }

    public Integer getFetchSize() {
        return this.fetchSize;
    }
}