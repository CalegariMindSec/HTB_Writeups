package org.hibernate.query.sqm;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/query/sqm/ComparisonOperator.class */
public enum ComparisonOperator {
    EQUAL { // from class: org.hibernate.query.sqm.ComparisonOperator.1
        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator negated() {
            return NOT_EQUAL;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator invert() {
            return EQUAL;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator broader() {
            return EQUAL;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator sharper() {
            return EQUAL;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public String sqlText() {
            return "=";
        }
    },
    NOT_EQUAL { // from class: org.hibernate.query.sqm.ComparisonOperator.2
        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator negated() {
            return EQUAL;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator invert() {
            return NOT_EQUAL;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator broader() {
            return NOT_EQUAL;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator sharper() {
            return NOT_EQUAL;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public String sqlText() {
            return "!=";
        }
    },
    NOT_DISTINCT_FROM { // from class: org.hibernate.query.sqm.ComparisonOperator.3
        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator negated() {
            return DISTINCT_FROM;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator invert() {
            return NOT_DISTINCT_FROM;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator broader() {
            return NOT_DISTINCT_FROM;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator sharper() {
            return NOT_DISTINCT_FROM;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public String sqlText() {
            return " is not distinct from ";
        }
    },
    DISTINCT_FROM { // from class: org.hibernate.query.sqm.ComparisonOperator.4
        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator negated() {
            return NOT_DISTINCT_FROM;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator invert() {
            return DISTINCT_FROM;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator broader() {
            return DISTINCT_FROM;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator sharper() {
            return DISTINCT_FROM;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public String sqlText() {
            return " is distinct from ";
        }
    },
    LESS_THAN { // from class: org.hibernate.query.sqm.ComparisonOperator.5
        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator negated() {
            return GREATER_THAN_OR_EQUAL;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator invert() {
            return GREATER_THAN;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator broader() {
            return LESS_THAN_OR_EQUAL;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator sharper() {
            return LESS_THAN;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public String sqlText() {
            return "<";
        }
    },
    LESS_THAN_OR_EQUAL { // from class: org.hibernate.query.sqm.ComparisonOperator.6
        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator negated() {
            return GREATER_THAN;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator invert() {
            return GREATER_THAN_OR_EQUAL;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator broader() {
            return LESS_THAN_OR_EQUAL;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator sharper() {
            return LESS_THAN;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public String sqlText() {
            return "<=";
        }
    },
    GREATER_THAN { // from class: org.hibernate.query.sqm.ComparisonOperator.7
        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator negated() {
            return LESS_THAN_OR_EQUAL;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator invert() {
            return LESS_THAN;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator broader() {
            return GREATER_THAN_OR_EQUAL;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator sharper() {
            return GREATER_THAN;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public String sqlText() {
            return ">";
        }
    },
    GREATER_THAN_OR_EQUAL { // from class: org.hibernate.query.sqm.ComparisonOperator.8
        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator negated() {
            return LESS_THAN;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator invert() {
            return LESS_THAN_OR_EQUAL;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator broader() {
            return GREATER_THAN_OR_EQUAL;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public ComparisonOperator sharper() {
            return GREATER_THAN;
        }

        @Override // org.hibernate.query.sqm.ComparisonOperator
        public String sqlText() {
            return ">=";
        }
    };

    public abstract ComparisonOperator negated();

    public abstract ComparisonOperator invert();

    public abstract ComparisonOperator broader();

    public abstract ComparisonOperator sharper();

    public abstract String sqlText();
}