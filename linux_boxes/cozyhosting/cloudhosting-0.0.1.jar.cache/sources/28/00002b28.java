package org.hibernate.annotations;

import java.util.Locale;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/annotations/PolymorphismType.class */
public enum PolymorphismType {
    IMPLICIT,
    EXPLICIT;

    public static PolymorphismType fromExternalValue(Object externalValue) {
        PolymorphismType[] values;
        if (externalValue != null) {
            if (externalValue instanceof PolymorphismType) {
                return (PolymorphismType) externalValue;
            }
            String externalValueStr = externalValue.toString();
            for (PolymorphismType checkType : values()) {
                if (checkType.name().equalsIgnoreCase(externalValueStr)) {
                    return checkType;
                }
            }
            return null;
        }
        return null;
    }

    public String getExternalForm() {
        return name().toLowerCase(Locale.ROOT);
    }
}