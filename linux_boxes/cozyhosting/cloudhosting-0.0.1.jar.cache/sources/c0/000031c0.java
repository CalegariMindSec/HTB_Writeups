package org.hibernate.dialect;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/dialect/ResultColumnReferenceStrategy.class */
public enum ResultColumnReferenceStrategy {
    SOURCE,
    ALIAS,
    ORDINAL;

    public static ResultColumnReferenceStrategy resolveByName(String name) {
        if (ALIAS.name().equalsIgnoreCase(name)) {
            return ALIAS;
        }
        if (ORDINAL.name().equalsIgnoreCase(name)) {
            return ORDINAL;
        }
        return SOURCE;
    }
}