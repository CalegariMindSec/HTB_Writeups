package org.hibernate.internal.util;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/MutableLong.class */
public class MutableLong {
    private long value;

    public MutableLong() {
    }

    public MutableLong(long value) {
        this.value = value;
    }

    public MutableLong deepCopy() {
        return new MutableLong(this.value);
    }

    public long getAndIncrement() {
        long j = this.value;
        this.value = j + 1;
        return j;
    }

    public long incrementAndGet() {
        long j = this.value + 1;
        this.value = j;
        return j;
    }

    public long get() {
        return this.value;
    }

    public void set(long value) {
        this.value = value;
    }

    public void increase() {
        this.value++;
    }
}