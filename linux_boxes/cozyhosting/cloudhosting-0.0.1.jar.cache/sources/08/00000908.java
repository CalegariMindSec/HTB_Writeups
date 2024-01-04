package io.micrometer.core.instrument.distribution;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/micrometer-core-1.10.3.jar:io/micrometer/core/instrument/distribution/TimeWindowSum.class */
public class TimeWindowSum {
    private static final AtomicIntegerFieldUpdater<TimeWindowSum> rotatingUpdater = AtomicIntegerFieldUpdater.newUpdater(TimeWindowSum.class, "rotating");
    private final long durationBetweenRotatesMillis;
    private final AtomicLong[] ringBuffer;
    private volatile int rotating;
    private volatile long lastRotateTimestampMillis = System.currentTimeMillis();
    private int currentBucket = 0;

    public TimeWindowSum(int bufferLength, Duration expiry) {
        this.durationBetweenRotatesMillis = expiry.toMillis();
        this.ringBuffer = new AtomicLong[bufferLength];
        for (int i = 0; i < bufferLength; i++) {
            this.ringBuffer[i] = new AtomicLong();
        }
    }

    public void record(long sampleMillis) {
        AtomicLong[] atomicLongArr;
        rotate();
        for (AtomicLong sum : this.ringBuffer) {
            sum.addAndGet(sampleMillis);
        }
    }

    public double poll() {
        double d;
        rotate();
        synchronized (this) {
            d = this.ringBuffer[this.currentBucket].get();
        }
        return d;
    }

    private void rotate() {
        long timeSinceLastRotateMillis = System.currentTimeMillis() - this.lastRotateTimestampMillis;
        if (timeSinceLastRotateMillis < this.durationBetweenRotatesMillis || !rotatingUpdater.compareAndSet(this, 0, 1)) {
            return;
        }
        int iterations = 0;
        try {
            synchronized (this) {
                do {
                    this.ringBuffer[this.currentBucket].set(0L);
                    int i = this.currentBucket + 1;
                    this.currentBucket = i;
                    if (i >= this.ringBuffer.length) {
                        this.currentBucket = 0;
                    }
                    timeSinceLastRotateMillis -= this.durationBetweenRotatesMillis;
                    this.lastRotateTimestampMillis += this.durationBetweenRotatesMillis;
                    if (timeSinceLastRotateMillis < this.durationBetweenRotatesMillis) {
                        break;
                    }
                    iterations++;
                } while (iterations < this.ringBuffer.length);
            }
        } finally {
            this.rotating = 0;
        }
    }
}