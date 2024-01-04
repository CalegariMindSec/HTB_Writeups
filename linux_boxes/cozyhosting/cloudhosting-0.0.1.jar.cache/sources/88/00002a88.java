package org.hibernate.action.internal;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/action/internal/DelayedPostInsertIdentifier.class */
public class DelayedPostInsertIdentifier implements Serializable, Comparable<DelayedPostInsertIdentifier> {
    private static final AtomicLong SEQUENCE = new AtomicLong();
    private final long identifier;

    public DelayedPostInsertIdentifier() {
        long value = SEQUENCE.incrementAndGet();
        if (value < 0) {
            synchronized (SEQUENCE) {
                value = SEQUENCE.incrementAndGet();
                if (value < 0) {
                    SEQUENCE.set(0L);
                    value = 0;
                }
            }
        }
        this.identifier = value;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DelayedPostInsertIdentifier that = (DelayedPostInsertIdentifier) o;
        return this.identifier == that.identifier;
    }

    public int hashCode() {
        return (int) (this.identifier ^ (this.identifier >>> 32));
    }

    public String toString() {
        return "<delayed:" + this.identifier + ">";
    }

    @Override // java.lang.Comparable
    public int compareTo(DelayedPostInsertIdentifier that) {
        return Long.compare(this.identifier, that.identifier);
    }
}