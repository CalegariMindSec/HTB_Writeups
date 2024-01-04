package org.HdrHistogram;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/HdrHistogram-2.1.12.jar:org/HdrHistogram/DoubleValueRecorder.class */
public interface DoubleValueRecorder {
    void recordValue(double d) throws ArrayIndexOutOfBoundsException;

    void recordValueWithCount(double d, long j) throws ArrayIndexOutOfBoundsException;

    void recordValueWithExpectedInterval(double d, double d2) throws ArrayIndexOutOfBoundsException;

    void reset();
}