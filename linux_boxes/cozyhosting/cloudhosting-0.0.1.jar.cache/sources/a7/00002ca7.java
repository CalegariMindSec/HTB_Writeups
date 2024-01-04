package org.hibernate.boot.jaxb.hbm.transform;

import java.util.function.Function;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/transform/UnsupportedFeatureHandling.class */
public enum UnsupportedFeatureHandling {
    ERROR,
    WARN,
    IGNORE,
    PICK;

    public static UnsupportedFeatureHandling fromSetting(Object value) {
        return fromSetting(value, v -> {
            return null;
        });
    }

    public static UnsupportedFeatureHandling fromSetting(Object value, UnsupportedFeatureHandling defaultValue) {
        return fromSetting(value, v -> {
            return defaultValue;
        });
    }

    public static UnsupportedFeatureHandling fromSetting(Object value, Function<Object, UnsupportedFeatureHandling> defaultValueSupplier) {
        if (value != null) {
            if (value instanceof UnsupportedFeatureHandling) {
                return (UnsupportedFeatureHandling) value;
            }
            if (ERROR.name().equalsIgnoreCase(value.toString())) {
                return ERROR;
            }
            if (IGNORE.name().equalsIgnoreCase(value.toString())) {
                return IGNORE;
            }
            if (PICK.name().equalsIgnoreCase(value.toString())) {
                return PICK;
            }
        }
        return defaultValueSupplier.apply(value);
    }
}