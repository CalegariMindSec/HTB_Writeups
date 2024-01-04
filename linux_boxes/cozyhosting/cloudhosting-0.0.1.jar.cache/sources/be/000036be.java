package org.hibernate.internal.util.collections;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/JoinedList.class */
public class JoinedList<E> extends AbstractList<E> {
    private final List<List<E>> lists;
    private final int size;

    public JoinedList(List<List<E>> lists) {
        this.lists = lists;
        this.size = ((Integer) lists.stream().map((v0) -> {
            return v0.size();
        }).reduce(0, (v0, v1) -> {
            return Integer.sum(v0, v1);
        })).intValue();
    }

    @SafeVarargs
    public JoinedList(List<E>... lists) {
        this(Arrays.asList(lists));
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int index) {
        for (List<E> list : this.lists) {
            if (list.size() > index) {
                return list.get(index);
            }
            index -= list.size();
        }
        return null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<E> iterator() {
        return this.lists.stream().flatMap((v0) -> {
            return v0.stream();
        }).iterator();
    }
}