package org.HdrHistogram;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/HdrHistogram-2.1.12.jar:org/HdrHistogram/ValueRecorder.class */
public interface ValueRecorder {
    void recordValue(long j) throws ArrayIndexOutOfBoundsException;

    void recordValueWithCount(long j, long j2) throws ArrayIndexOutOfBoundsException;

    void recordValueWithExpectedInterval(long j, long j2) throws ArrayIndexOutOfBoundsException;

    void reset();
}