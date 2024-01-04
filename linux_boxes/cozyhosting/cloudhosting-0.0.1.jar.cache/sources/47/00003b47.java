package org.hibernate.query.sqm;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/query/sqm/SortOrder.class */
public enum SortOrder {
    ASCENDING { // from class: org.hibernate.query.sqm.SortOrder.1
        @Override // org.hibernate.query.sqm.SortOrder
        public SortOrder reverse() {
            return DESCENDING;
        }
    },
    DESCENDING { // from class: org.hibernate.query.sqm.SortOrder.2
        @Override // org.hibernate.query.sqm.SortOrder
        public SortOrder reverse() {
            return ASCENDING;
        }
    };

    public abstract SortOrder reverse();

    public static SortOrder interpret(String value) {
        if (value == null) {
            return null;
        }
        if (value.equalsIgnoreCase("ascending") || value.equalsIgnoreCase("asc")) {
            return ASCENDING;
        }
        if (value.equalsIgnoreCase("descending") || value.equalsIgnoreCase("desc")) {
            return DESCENDING;
        }
        throw new IllegalArgumentException("Unknown sort order : " + value);
    }
}