package org.hibernate.loader.internal;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/loader/internal/AliasConstantsHelper.class */
public final class AliasConstantsHelper {
    private static final int MAX_POOL_SIZE = 40;
    private static final String[] pool = initPool(40);

    public static String get(int i) {
        if (i < 40 && i >= 0) {
            return pool[i];
        }
        return internalAlias(i);
    }

    private static String[] initPool(int maxPoolSize) {
        String[] pool2 = new String[maxPoolSize];
        for (int i = 0; i < maxPoolSize; i++) {
            pool2[i] = internalAlias(i);
        }
        return pool2;
    }

    private static String internalAlias(int i) {
        return Integer.toString(i) + "_";
    }
}