package org.aspectj.runtime.internal.cflowstack;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/runtime/internal/cflowstack/ThreadCounter.class */
public interface ThreadCounter {
    void inc();

    void dec();

    boolean isNotZero();

    void removeThreadCounter();
}