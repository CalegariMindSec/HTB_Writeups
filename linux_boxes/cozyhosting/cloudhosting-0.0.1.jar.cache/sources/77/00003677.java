package org.hibernate.internal.util;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/MathHelper.class */
public final class MathHelper {
    private MathHelper() {
    }

    public static int ceilingPowerOfTwo(int value) {
        return 1 << (-Integer.numberOfLeadingZeros(value - 1));
    }
}