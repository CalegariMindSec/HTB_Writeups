package org.hibernate.query.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/query/spi/Limit.class */
public class Limit {
    public static final Limit NONE = new Limit();
    private Integer firstRow;
    private Integer maxRows;

    public Limit() {
    }

    public Limit(Integer firstRow, Integer maxRows) {
        this.firstRow = firstRow;
        this.maxRows = maxRows;
    }

    public boolean isEmpty() {
        return this.firstRow == null && this.maxRows == null;
    }

    public Limit makeCopy() {
        return new Limit(this.firstRow, this.maxRows);
    }

    public Integer getFirstRow() {
        return this.firstRow;
    }

    public int getFirstRowJpa() {
        if (this.firstRow == null) {
            return 0;
        }
        return this.firstRow.intValue();
    }

    public void setFirstRow(Integer firstRow) {
        this.firstRow = firstRow;
    }

    public Integer getMaxRows() {
        return this.maxRows;
    }

    public int getMaxRowsJpa() {
        if (this.maxRows == null) {
            return Integer.MAX_VALUE;
        }
        return this.maxRows.intValue();
    }

    public void setMaxRows(int maxRows) {
        if (maxRows < 0) {
            this.maxRows = null;
        } else {
            this.maxRows = Integer.valueOf(maxRows);
        }
    }

    public void setMaxRows(Integer maxRows) {
        if (maxRows != null && maxRows.intValue() < 0) {
            this.maxRows = null;
        } else {
            this.maxRows = maxRows;
        }
    }

    public boolean isCompatible(Limit limit) {
        if (limit == null) {
            return isEmpty();
        }
        if (this == limit) {
            return true;
        }
        if (this.firstRow != null) {
            if (!this.firstRow.equals(limit.firstRow)) {
                return false;
            }
        } else if (limit.firstRow != null) {
            return false;
        }
        return this.maxRows != null ? this.maxRows.equals(limit.maxRows) : limit.maxRows == null;
    }
}