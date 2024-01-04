package org.postgresql.jdbc;

import java.util.Locale;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/jdbc/AutoSave.class */
public enum AutoSave {
    NEVER,
    ALWAYS,
    CONSERVATIVE;
    
    private final String value = name().toLowerCase(Locale.ROOT);

    AutoSave() {
    }

    public String value() {
        return this.value;
    }

    public static AutoSave of(String value) {
        return valueOf(value.toUpperCase(Locale.ROOT));
    }
}