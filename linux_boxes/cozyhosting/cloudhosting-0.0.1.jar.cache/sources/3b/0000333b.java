package org.hibernate.engine.jdbc.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/jdbc/spi/TypeNullability.class */
public enum TypeNullability {
    NULLABLE,
    NON_NULLABLE,
    UNKNOWN;

    public static TypeNullability interpret(short code) {
        switch (code) {
            case 0:
                return NON_NULLABLE;
            case 1:
                return NULLABLE;
            case 2:
                return UNKNOWN;
            default:
                throw new IllegalArgumentException("Unknown type nullability code [" + code + "] encountered");
        }
    }
}