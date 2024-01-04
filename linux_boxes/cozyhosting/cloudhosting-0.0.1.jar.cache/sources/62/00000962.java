package io.micrometer.core.instrument.util;

import java.util.AbstractList;
import java.util.List;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/micrometer-core-1.10.3.jar:io/micrometer/core/instrument/util/AbstractPartition.class */
public abstract class AbstractPartition<T> extends AbstractList<List<T>> {
    final List<T> delegate;
    final int partitionSize;
    final int partitionCount;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractPartition(List<T> delegate, int partitionSize) {
        if (delegate == null) {
            throw new NullPointerException("delegate == null");
        }
        this.delegate = delegate;
        if (partitionSize < 1) {
            throw new IllegalArgumentException("partitionSize < 1");
        }
        this.partitionSize = partitionSize;
        this.partitionCount = partitionCount(delegate, partitionSize);
    }

    @Override // java.util.AbstractList, java.util.List
    public List<T> get(int index) {
        int start = index * this.partitionSize;
        int end = Math.min(start + this.partitionSize, this.delegate.size());
        return this.delegate.subList(start, end);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.partitionCount;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }

    static <T> int partitionCount(List<T> delegate, int partitionSize) {
        int result = delegate.size() / partitionSize;
        return delegate.size() % partitionSize == 0 ? result : result + 1;
    }
}