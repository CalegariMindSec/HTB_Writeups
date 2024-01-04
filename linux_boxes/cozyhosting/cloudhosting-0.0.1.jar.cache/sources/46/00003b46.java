package org.hibernate.query.sqm;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/query/sqm/SetOperator.class */
public enum SetOperator {
    UNION("union"),
    UNION_ALL("union all"),
    INTERSECT("intersect"),
    INTERSECT_ALL("intersect all"),
    EXCEPT("except"),
    EXCEPT_ALL("except all");
    
    private final String sqlString;

    SetOperator(String sqlString) {
        this.sqlString = sqlString;
    }

    public String sqlString() {
        return this.sqlString;
    }
}