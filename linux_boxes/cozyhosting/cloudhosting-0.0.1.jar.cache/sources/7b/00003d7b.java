package org.hibernate.sql.ast;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/sql/ast/SqlAstJoinType.class */
public enum SqlAstJoinType {
    INNER(""),
    LEFT("left "),
    RIGHT("right "),
    CROSS("cross "),
    FULL("full ");
    
    private final String text;

    SqlAstJoinType(String text) {
        this.text = text;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.text;
    }

    public String getText() {
        return this.text;
    }
}