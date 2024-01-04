package org.hibernate.internal.util;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/MutableInteger.class */
public class MutableInteger {
    private int value;

    public MutableInteger() {
        this(0);
    }

    public MutableInteger(int value) {
        this.value = value;
    }

    public MutableInteger deepCopy() {
        return new MutableInteger(this.value);
    }

    public int getAndIncrement() {
        int i = this.value;
        this.value = i + 1;
        return i;
    }

    public int getAndIncrementBy(int i) {
        int local = this.value;
        this.value += i;
        return local;
    }

    public int incrementAndGet() {
        int i = this.value + 1;
        this.value = i;
        return i;
    }

    public int get() {
        return this.value;
    }

    public void set(int value) {
        this.value = value;
    }

    public void increase() {
        this.value++;
    }

    public void increment() {
        this.value++;
    }

    public void incrementBy(int i) {
        this.value += i;
    }

    public void increase(int i) {
        this.value += i;
    }

    public void plus(int i) {
        this.value += i;
    }

    public void minus(int i) {
        this.value -= i;
    }
}