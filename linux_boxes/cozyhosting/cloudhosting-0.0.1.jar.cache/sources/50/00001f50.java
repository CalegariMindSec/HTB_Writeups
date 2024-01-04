package org.apache.logging.log4j.util;

import java.util.Locale;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/log4j-api-2.19.0.jar:org/apache/logging/log4j/util/EnglishEnums.class */
public final class EnglishEnums {
    private EnglishEnums() {
    }

    public static <T extends Enum<T>> T valueOf(final Class<T> enumType, final String name) {
        return (T) valueOf(enumType, name, null);
    }

    public static <T extends Enum<T>> T valueOf(final Class<T> enumType, final String name, final T defaultValue) {
        return name == null ? defaultValue : (T) Enum.valueOf(enumType, name.toUpperCase(Locale.ENGLISH));
    }
}