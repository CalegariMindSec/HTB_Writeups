package org.hibernate;

import org.postgresql.core.Oid;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/ScrollMode.class */
public enum ScrollMode {
    FORWARD_ONLY(Oid.NAME_ARRAY),
    SCROLL_SENSITIVE(1005),
    SCROLL_INSENSITIVE(1004);
    
    private final int resultSetType;

    ScrollMode(int level) {
        this.resultSetType = level;
    }

    public int toResultSetType() {
        return this.resultSetType;
    }

    public boolean lessThan(ScrollMode other) {
        return this.resultSetType < other.resultSetType;
    }
}