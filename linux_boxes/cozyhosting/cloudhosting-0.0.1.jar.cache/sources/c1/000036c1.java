package org.hibernate.internal.util.collections;

import java.util.Iterator;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/SingletonIterator.class */
public final class SingletonIterator<T> implements Iterator<T> {
    private final T value;
    private boolean hasNext = true;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override // java.util.Iterator
    public T next() {
        if (this.hasNext) {
            this.hasNext = false;
            return this.value;
        }
        throw new IllegalStateException();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public SingletonIterator(T value) {
        this.value = value;
    }
}