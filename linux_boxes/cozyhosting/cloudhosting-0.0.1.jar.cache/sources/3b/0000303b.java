package org.hibernate.cache.spi.support;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.aspectj.apache.bcel.Constants;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/cache/spi/support/SimpleTimestamper.class */
public final class SimpleTimestamper {
    private static final int BIN_DIGITS = 12;
    private static final AtomicLong VALUE = new AtomicLong();
    public static final short ONE_MS = 4096;

    public static long next() {
        while (true) {
            long base = System.currentTimeMillis() << 12;
            long maxValue = (base + Constants.NEGATABLE) - 1;
            long current = VALUE.get();
            long max = Math.max(base, current + 1);
            while (true) {
                long update = max;
                if (update < maxValue) {
                    if (!VALUE.compareAndSet(current, update)) {
                        current = VALUE.get();
                        max = Math.max(base, current + 1);
                    } else {
                        return update;
                    }
                }
            }
        }
    }

    public static int timeOut() {
        return ((int) TimeUnit.SECONDS.toMillis(60L)) * 4096;
    }

    private SimpleTimestamper() {
    }
}