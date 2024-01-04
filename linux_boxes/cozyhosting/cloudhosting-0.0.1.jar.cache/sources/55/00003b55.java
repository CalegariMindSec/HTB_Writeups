package org.hibernate.query.sqm;

import java.util.Locale;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/query/sqm/TrimSpec.class */
public enum TrimSpec {
    LEADING,
    TRAILING,
    BOTH;

    public String toSqlText() {
        return name().toLowerCase(Locale.ROOT);
    }
}