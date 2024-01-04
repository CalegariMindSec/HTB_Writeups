package org.hibernate.internal.util.collections;

import java.util.function.Consumer;
import java.util.function.Function;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/Stack.class */
public interface Stack<T> {
    void push(T t);

    T pop();

    T getCurrent();

    int depth();

    boolean isEmpty();

    void clear();

    void visitRootFirst(Consumer<T> consumer);

    <X> X findCurrentFirst(Function<T, X> function);
}