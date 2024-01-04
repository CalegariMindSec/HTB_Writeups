package org.hibernate;

import java.io.Closeable;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/ScrollableResults.class */
public interface ScrollableResults<R> extends AutoCloseable, Closeable {
    R get();

    @Override // java.lang.AutoCloseable, java.io.Closeable
    void close();

    boolean next();

    boolean previous();

    boolean scroll(int i);

    boolean position(int i);

    boolean last();

    boolean first();

    void beforeFirst();

    void afterLast();

    boolean isFirst();

    boolean isLast();

    int getRowNumber();

    boolean setRowNumber(int i);

    void setFetchSize(int i);
}