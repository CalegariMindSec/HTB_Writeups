package org.hibernate.engine.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/spi/ExecuteUpdateResultCheckStyle.class */
public enum ExecuteUpdateResultCheckStyle {
    NONE("none"),
    COUNT("rowcount"),
    PARAM("param");
    
    private final String name;

    ExecuteUpdateResultCheckStyle(String name) {
        this.name = name;
    }

    public String externalName() {
        return this.name;
    }

    public static ExecuteUpdateResultCheckStyle fromExternalName(String name) {
        if (name.equalsIgnoreCase(NONE.name)) {
            return NONE;
        }
        if (name.equalsIgnoreCase(COUNT.name)) {
            return COUNT;
        }
        if (name.equalsIgnoreCase(PARAM.name)) {
            return PARAM;
        }
        return null;
    }

    public static ExecuteUpdateResultCheckStyle determineDefault(String customSql, boolean callable) {
        if (customSql == null) {
            return COUNT;
        }
        return callable ? PARAM : COUNT;
    }
}