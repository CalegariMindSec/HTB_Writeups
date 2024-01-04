package org.LatencyUtils;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/LatencyUtils-2.0.3.jar:org/LatencyUtils/IntervalEstimator.class */
public abstract class IntervalEstimator {
    public abstract void recordInterval(long j);

    public abstract long getEstimatedInterval(long j);
}