package org.postgresql.core.v3.adaptivefetch;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/core/v3/adaptivefetch/AdaptiveFetchCacheEntry.class */
public class AdaptiveFetchCacheEntry {
    private int size = -1;
    private int counter = 0;
    private int maximumRowSizeBytes = -1;

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCounter() {
        return this.counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getMaximumRowSizeBytes() {
        return this.maximumRowSizeBytes;
    }

    public void setMaximumRowSizeBytes(int maximumRowSizeBytes) {
        this.maximumRowSizeBytes = maximumRowSizeBytes;
    }

    public void incrementCounter() {
        this.counter++;
    }

    public void decrementCounter() {
        this.counter--;
    }
}