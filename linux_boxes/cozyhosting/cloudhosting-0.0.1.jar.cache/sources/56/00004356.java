package org.postgresql.jdbc;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/jdbc/EscapeSyntaxCallMode.class */
public enum EscapeSyntaxCallMode {
    SELECT("select"),
    CALL_IF_NO_RETURN("callIfNoReturn"),
    CALL("call");
    
    private final String value;

    EscapeSyntaxCallMode(String value) {
        this.value = value;
    }

    public static EscapeSyntaxCallMode of(String mode) {
        EscapeSyntaxCallMode[] values;
        for (EscapeSyntaxCallMode escapeSyntaxCallMode : values()) {
            if (escapeSyntaxCallMode.value.equals(mode)) {
                return escapeSyntaxCallMode;
            }
        }
        return SELECT;
    }

    public String value() {
        return this.value;
    }
}