package org.hibernate.boot.model;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/model/TruthValue.class */
public enum TruthValue {
    TRUE,
    FALSE,
    UNKNOWN;

    public boolean toBoolean(boolean defaultValue) {
        switch (this) {
            case TRUE:
                return true;
            case FALSE:
                return false;
            default:
                return defaultValue;
        }
    }

    public static boolean toBoolean(TruthValue value, boolean defaultValue) {
        return value != null ? value.toBoolean(defaultValue) : defaultValue;
    }
}