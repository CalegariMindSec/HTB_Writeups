package io.micrometer.core.instrument;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/micrometer-core-1.10.3.jar:io/micrometer/core/instrument/Clock.class */
public interface Clock {
    public static final Clock SYSTEM = new Clock() { // from class: io.micrometer.core.instrument.Clock.1
        @Override // io.micrometer.core.instrument.Clock
        public long wallTime() {
            return System.currentTimeMillis();
        }

        @Override // io.micrometer.core.instrument.Clock
        public long monotonicTime() {
            return System.nanoTime();
        }
    };

    long wallTime();

    long monotonicTime();
}