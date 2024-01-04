package org.LatencyUtils;

import java.util.Comparator;
import java.util.concurrent.CancellationException;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/LatencyUtils-2.0.3.jar:org/LatencyUtils/TimeServices.class */
public class TimeServices {
    public static final boolean useActualTime;
    private static long currentTime;
    private static final Object timeUpdateMonitor = new Object();

    static {
        String useActualTimeProperty = System.getProperty("LatencyUtils.useActualTime", "true");
        useActualTime = !useActualTimeProperty.equals("false");
    }

    public static long nanoTime() {
        if (useActualTime) {
            return System.nanoTime();
        }
        return currentTime;
    }

    public static long currentTimeMillis() {
        if (useActualTime) {
            return System.currentTimeMillis();
        }
        return currentTime / 1000000;
    }

    public static void sleepMsecs(long sleepTimeMsec) {
        try {
            if (useActualTime) {
                Thread.sleep(sleepTimeMsec);
            } else {
                waitUntilTime(currentTime + (sleepTimeMsec * 1000000));
            }
        } catch (InterruptedException e) {
        }
    }

    public static void sleepNanos(long sleepTimeNsec) {
        try {
            if (useActualTime) {
                TimeUnit.NANOSECONDS.sleep(sleepTimeNsec);
            } else {
                waitUntilTime(currentTime + sleepTimeNsec);
            }
        } catch (InterruptedException e) {
        }
    }

    public static void waitUntilTime(long timeToWakeAt) throws InterruptedException {
        synchronized (timeUpdateMonitor) {
            while (timeToWakeAt > currentTime) {
                timeUpdateMonitor.wait();
            }
        }
    }

    public static void moveTimeForward(long timeDeltaNsec) throws InterruptedException {
        setCurrentTime(currentTime + timeDeltaNsec);
    }

    public static void moveTimeForwardMsec(long timeDeltaMsec) throws InterruptedException {
        moveTimeForward(timeDeltaMsec * 1000000);
    }

    public static void setCurrentTime(long newCurrentTime) throws InterruptedException {
        if (newCurrentTime < nanoTime()) {
            throw new IllegalStateException("Can't set current time to the past.");
        }
        if (useActualTime) {
            while (newCurrentTime > nanoTime()) {
                TimeUnit.NANOSECONDS.sleep(newCurrentTime - nanoTime());
            }
            return;
        }
        while (currentTime < newCurrentTime) {
            long timeDelta = Math.min(newCurrentTime - currentTime, 5000000L);
            currentTime += timeDelta;
            synchronized (timeUpdateMonitor) {
                timeUpdateMonitor.notifyAll();
                TimeUnit.NANOSECONDS.sleep(50000L);
            }
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/LatencyUtils-2.0.3.jar:org/LatencyUtils/TimeServices$ScheduledExecutor.class */
    public static class ScheduledExecutor {
        private final ScheduledThreadPoolExecutor actualExecutor;
        final MyExecutorThread internalExecutorThread;
        final PriorityBlockingQueue<RunnableTaskEntry> taskEntries;
        private static CompareRunnableTaskEntryByStartTime compareRunnableTaskEntryByStartTime = new CompareRunnableTaskEntryByStartTime();

        /* JADX INFO: Access modifiers changed from: package-private */
        public ScheduledExecutor() {
            if (TimeServices.useActualTime) {
                this.actualExecutor = new ScheduledThreadPoolExecutor(1);
                this.internalExecutorThread = null;
                this.taskEntries = null;
                return;
            }
            this.actualExecutor = null;
            this.taskEntries = new PriorityBlockingQueue<>(10000, compareRunnableTaskEntryByStartTime);
            this.internalExecutorThread = new MyExecutorThread();
            this.internalExecutorThread.setDaemon(true);
            this.internalExecutorThread.start();
        }

        public void scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
            if (!TimeServices.useActualTime) {
                long startTimeNsec = TimeServices.currentTime + TimeUnit.NANOSECONDS.convert(initialDelay, unit);
                long periodNsec = TimeUnit.NANOSECONDS.convert(period, unit);
                RunnableTaskEntry entry = new RunnableTaskEntry(command, startTimeNsec, periodNsec, true);
                synchronized (TimeServices.timeUpdateMonitor) {
                    this.taskEntries.add(entry);
                    TimeServices.timeUpdateMonitor.notifyAll();
                }
                return;
            }
            this.actualExecutor.scheduleAtFixedRate(command, initialDelay, period, unit);
        }

        public void shutdown() {
            if (TimeServices.useActualTime) {
                this.actualExecutor.shutdownNow();
            } else {
                this.internalExecutorThread.terminate();
            }
        }

        /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/LatencyUtils-2.0.3.jar:org/LatencyUtils/TimeServices$ScheduledExecutor$MyExecutorThread.class */
        private class MyExecutorThread extends Thread {
            volatile boolean doRun;

            private MyExecutorThread() {
                this.doRun = true;
            }

            void terminate() {
                synchronized (TimeServices.timeUpdateMonitor) {
                    this.doRun = false;
                    TimeServices.timeUpdateMonitor.notifyAll();
                }
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                while (this.doRun) {
                    try {
                        synchronized (TimeServices.timeUpdateMonitor) {
                            RunnableTaskEntry entry = ScheduledExecutor.this.taskEntries.peek();
                            while (entry != null && entry.getStartTime() < TimeServices.currentTime) {
                                entry.getCommand().run();
                                if (entry.shouldReschedule()) {
                                    entry.setNewStartTime(TimeServices.currentTime);
                                    ScheduledExecutor.this.taskEntries.add(entry);
                                }
                                entry = ScheduledExecutor.this.taskEntries.peek();
                            }
                            TimeServices.timeUpdateMonitor.wait();
                        }
                    } catch (InterruptedException e) {
                        return;
                    } catch (CancellationException e2) {
                        return;
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/LatencyUtils-2.0.3.jar:org/LatencyUtils/TimeServices$ScheduledExecutor$RunnableTaskEntry.class */
        public static class RunnableTaskEntry {
            long startTime;
            Runnable command;
            long period;
            long initialStartTime;
            long executionCount;
            boolean fixedRate;

            RunnableTaskEntry(Runnable command, long startTimeNsec, long periodNsec, boolean fixedRate) {
                this.command = command;
                this.startTime = startTimeNsec;
                this.initialStartTime = startTimeNsec;
                this.period = periodNsec;
                this.fixedRate = fixedRate;
            }

            boolean shouldReschedule() {
                return this.period != 0;
            }

            public long getStartTime() {
                return this.startTime;
            }

            public Runnable getCommand() {
                return this.command;
            }

            public void setNewStartTime(long timeNow) {
                if (this.period == 0) {
                    throw new IllegalStateException("should nto try to reschedule an entry that has no interval or rare");
                }
                if (!this.fixedRate) {
                    this.startTime = timeNow + this.period;
                    return;
                }
                this.executionCount++;
                this.startTime = this.initialStartTime + (this.executionCount * this.period);
            }
        }

        /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/LatencyUtils-2.0.3.jar:org/LatencyUtils/TimeServices$ScheduledExecutor$CompareRunnableTaskEntryByStartTime.class */
        static class CompareRunnableTaskEntryByStartTime implements Comparator<RunnableTaskEntry> {
            CompareRunnableTaskEntryByStartTime() {
            }

            @Override // java.util.Comparator
            public int compare(RunnableTaskEntry r1, RunnableTaskEntry r2) {
                long t1 = r1.startTime;
                long t2 = r2.startTime;
                if (t1 > t2) {
                    return 1;
                }
                return t1 < t2 ? -1 : 0;
            }
        }
    }
}