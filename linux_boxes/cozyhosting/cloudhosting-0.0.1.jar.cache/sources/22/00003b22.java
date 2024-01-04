package org.hibernate.query.sqm;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/query/sqm/BinaryArithmeticOperator.class */
public enum BinaryArithmeticOperator {
    ADD { // from class: org.hibernate.query.sqm.BinaryArithmeticOperator.1
        @Override // org.hibernate.query.sqm.BinaryArithmeticOperator
        public String toLoggableText(String lhs, String rhs) {
            return BinaryArithmeticOperator.standardToLoggableText(lhs, this, rhs);
        }

        @Override // org.hibernate.query.sqm.BinaryArithmeticOperator
        public char getOperatorSqlText() {
            return '+';
        }
    },
    SUBTRACT { // from class: org.hibernate.query.sqm.BinaryArithmeticOperator.2
        @Override // org.hibernate.query.sqm.BinaryArithmeticOperator
        public String toLoggableText(String lhs, String rhs) {
            return BinaryArithmeticOperator.standardToLoggableText(lhs, this, rhs);
        }

        @Override // org.hibernate.query.sqm.BinaryArithmeticOperator
        public char getOperatorSqlText() {
            return '-';
        }
    },
    MULTIPLY { // from class: org.hibernate.query.sqm.BinaryArithmeticOperator.3
        @Override // org.hibernate.query.sqm.BinaryArithmeticOperator
        public String toLoggableText(String lhs, String rhs) {
            return BinaryArithmeticOperator.standardToLoggableText(lhs, this, rhs);
        }

        @Override // org.hibernate.query.sqm.BinaryArithmeticOperator
        public char getOperatorSqlText() {
            return '*';
        }
    },
    DIVIDE { // from class: org.hibernate.query.sqm.BinaryArithmeticOperator.4
        @Override // org.hibernate.query.sqm.BinaryArithmeticOperator
        public String toLoggableText(String lhs, String rhs) {
            return BinaryArithmeticOperator.standardToLoggableText(lhs, this, rhs);
        }

        @Override // org.hibernate.query.sqm.BinaryArithmeticOperator
        public char getOperatorSqlText() {
            return '/';
        }
    },
    QUOT { // from class: org.hibernate.query.sqm.BinaryArithmeticOperator.5
        @Override // org.hibernate.query.sqm.BinaryArithmeticOperator
        public String toLoggableText(String lhs, String rhs) {
            return BinaryArithmeticOperator.standardToLoggableText(lhs, this, rhs);
        }

        @Override // org.hibernate.query.sqm.BinaryArithmeticOperator
        public char getOperatorSqlText() {
            return '/';
        }
    },
    MODULO { // from class: org.hibernate.query.sqm.BinaryArithmeticOperator.6
        @Override // org.hibernate.query.sqm.BinaryArithmeticOperator
        public String toLoggableText(String lhs, String rhs) {
            return "mod(" + lhs + "," + rhs + ")";
        }

        @Override // org.hibernate.query.sqm.BinaryArithmeticOperator
        public char getOperatorSqlText() {
            return '%';
        }
    };

    public abstract String toLoggableText(String str, String str2);

    public abstract char getOperatorSqlText();

    public String getOperatorSqlTextString() {
        return Character.toString(getOperatorSqlText());
    }

    private static String standardToLoggableText(String lhs, BinaryArithmeticOperator operator, String rhs) {
        return standardToLoggableText(lhs, operator.getOperatorSqlText(), rhs);
    }

    private static String standardToLoggableText(String lhs, char operator, String rhs) {
        return "(" + lhs + operator + rhs + ")";
    }
}