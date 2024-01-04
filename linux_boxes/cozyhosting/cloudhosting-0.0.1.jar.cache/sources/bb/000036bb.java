package org.hibernate.internal.util.collections;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/JoinedIterable.class */
public class JoinedIterable<T> implements Iterable<T> {
    private final TypeSafeJoinedIterator<T> iterator;

    public JoinedIterable(List<Iterable<T>> iterables) {
        if (iterables == null) {
            throw new NullPointerException("Unexpected null iterables argument");
        }
        this.iterator = new TypeSafeJoinedIterator<>(iterables);
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return this.iterator;
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/JoinedIterable$TypeSafeJoinedIterator.class */
    private static class TypeSafeJoinedIterator<T> implements Iterator<T> {
        private List<Iterable<T>> iterables;
        private int currentIterableIndex;
        private Iterator<T> currentIterator;
        private Iterator<T> lastUsedIterator;

        public TypeSafeJoinedIterator(List<Iterable<T>> iterables) {
            this.iterables = iterables;
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
                if (this.iterables.size() == 0) {
                    this.currentIterator = Collections.emptyIterator();
                } else {
                    this.currentIterator = this.iterables.get(0).iterator();
                }
                this.lastUsedIterator = this.currentIterator;
            }
            while (!this.currentIterator.hasNext() && this.currentIterableIndex < this.iterables.size() - 1) {
                this.currentIterableIndex++;
                this.currentIterator = this.iterables.get(this.currentIterableIndex).iterator();
            }
        }
    }
}