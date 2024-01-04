package org.HdrHistogram;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/HdrHistogram-2.1.12.jar:org/HdrHistogram/WriterReaderPhaser.class */
public class WriterReaderPhaser {
    private volatile long startEpoch = 0;
    private volatile long evenEndEpoch = 0;
    private volatile long oddEndEpoch = Long.MIN_VALUE;
    private final ReentrantLock readerLock = new ReentrantLock();
    private static final AtomicLongFieldUpdater<WriterReaderPhaser> startEpochUpdater = AtomicLongFieldUpdater.newUpdater(WriterReaderPhaser.class, "startEpoch");
    private static final AtomicLongFieldUpdater<WriterReaderPhaser> evenEndEpochUpdater = AtomicLongFieldUpdater.newUpdater(WriterReaderPhaser.class, "evenEndEpoch");
    private static final AtomicLongFieldUpdater<WriterReaderPhaser> oddEndEpochUpdater = AtomicLongFieldUpdater.newUpdater(WriterReaderPhaser.class, "oddEndEpoch");

    public long writerCriticalSectionEnter() {
        return startEpochUpdater.getAndIncrement(this);
    }

    public void writerCriticalSectionExit(long criticalValueAtEnter) {
        (criticalValueAtEnter < 0 ? oddEndEpochUpdater : evenEndEpochUpdater).getAndIncrement(this);
    }

    public void readerLock() {
        this.readerLock.lock();
    }

    public void readerUnlock() {
        this.readerLock.unlock();
    }

    public void flipPhase(long yieldTimeNsec) {
        if (!this.readerLock.isHeldByCurrentThread()) {
            throw new IllegalStateException("flipPhase() can only be called while holding the readerLock()");
        }
        boolean nextPhaseIsEven = this.startEpoch < 0;
        long initialStartValue = nextPhaseIsEven ? 0L : Long.MIN_VALUE;
        (nextPhaseIsEven ? evenEndEpochUpdater : oddEndEpochUpdater).lazySet(this, initialStartValue);
        long startValueAtFlip = startEpochUpdater.getAndSet(this, initialStartValue);
        while (true) {
            if ((nextPhaseIsEven ? this.oddEndEpoch : this.evenEndEpoch) != startValueAtFlip) {
                if (yieldTimeNsec == 0) {
                    Thread.yield();
                } else {
                    try {
                        TimeUnit.NANOSECONDS.sleep(yieldTimeNsec);
                    } catch (InterruptedException e) {
                    }
                }
            } else {
                return;
            }
        }
    }

    public void flipPhase() {
        flipPhase(0L);
    }
}