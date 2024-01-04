package org.hibernate.engine.jdbc.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/jdbc/spi/TypeSearchability.class */
public enum TypeSearchability {
    NONE,
    FULL,
    CHAR,
    BASIC;

    public static TypeSearchability interpret(short code) {
        switch (code) {
            case 0:
                return NONE;
            case 1:
                return CHAR;
            case 2:
                return BASIC;
            case 3:
                return FULL;
            default:
                throw new IllegalArgumentException("Unknown type searchability code [" + code + "] encountered");
        }
    }
}