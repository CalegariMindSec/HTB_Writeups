package org.hibernate.internal.util.collections;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/JoinedIterator.class */
public class JoinedIterator<T> implements Iterator<T> {
    private final Iterator<? extends T>[] wrappedIterators;
    private int currentIteratorIndex;
    private Iterator<? extends T> currentIterator;
    private Iterator<? extends T> lastUsedIterator;

    public JoinedIterator(List<Iterator<T>> wrappedIterators) {
        this((Iterator[]) wrappedIterators.toArray(new Iterator[0]));
    }

    @SafeVarargs
    public JoinedIterator(Iterator<? extends T>... iteratorsToWrap) {
        if (iteratorsToWrap == null) {
            throw new NullPointerException("Iterators to join were null");
        }
        this.wrappedIterators = iteratorsToWrap;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        updateCurrentIterator();
        return this.currentIterator.hasNext();
    }

    @Override // java.util.Iterator
    public T next() {
        updateCurrentIterator();
        return this.currentIterator.next();
    }

    @Override // java.util.Iterator
    public void remove() {
        updateCurrentIterator();
        this.lastUsedIterator.remove();
    }

    protected void updateCurrentIterator() {
        if (this.currentIterator == null) {
            if (this.wrappedIterators.length == 0) {
                this.currentIterator = Collections.emptyIterator();
            } else {
                this.currentIterator = this.wrappedIterators[0];
            }
            this.lastUsedIterator = this.currentIterator;
        }
        while (!this.currentIterator.hasNext() && this.currentIteratorIndex < this.wrappedIterators.length - 1) {
            this.currentIteratorIndex++;
            this.currentIterator = this.wrappedIterators[this.currentIteratorIndex];
        }
    }
}