package org.hibernate.query.sqm;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/query/sqm/UnaryArithmeticOperator.class */
public enum UnaryArithmeticOperator {
    UNARY_PLUS('+'),
    UNARY_MINUS('-');
    
    private final char operatorChar;

    UnaryArithmeticOperator(char operatorChar) {
        this.operatorChar = operatorChar;
    }

    public char getOperatorChar() {
        return this.operatorChar;
    }
}