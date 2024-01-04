package org.postgresql.jdbc;

import org.hibernate.cache.internal.SimpleCacheKeysFactory;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/jdbc/PreferQueryMode.class */
public enum PreferQueryMode {
    SIMPLE(SimpleCacheKeysFactory.SHORT_NAME),
    EXTENDED_FOR_PREPARED("extendedForPrepared"),
    EXTENDED("extended"),
    EXTENDED_CACHE_EVERYTHING("extendedCacheEverything");
    
    private final String value;

    PreferQueryMode(String value) {
        this.value = value;
    }

    public static PreferQueryMode of(String mode) {
        PreferQueryMode[] values;
        for (PreferQueryMode preferQueryMode : values()) {
            if (preferQueryMode.value.equals(mode)) {
                return preferQueryMode;
            }
        }
        return EXTENDED;
    }

    public String value() {
        return this.value;
    }
}