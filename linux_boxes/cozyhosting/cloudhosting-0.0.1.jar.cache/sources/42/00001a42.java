package org.HdrHistogram.packedarray;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/HdrHistogram-2.1.12.jar:org/HdrHistogram/packedarray/IterationValue.class */
public class IterationValue {
    private int index;
    private long value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void set(int index, long value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return this.index;
    }

    public long getValue() {
        return this.value;
    }
}