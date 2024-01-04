package org.hibernate.internal.util.collections;

import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/IdentitySet.class */
public class IdentitySet<E> implements Set<E> {
    private static final Object DUMP_VALUE = new Object();
    private final IdentityHashMap<E, Object> map;

    public IdentitySet() {
        this.map = new IdentityHashMap<>();
    }

    public IdentitySet(int sizing) {
        this.map = new IdentityHashMap<>(sizing);
    }

    @Override // java.util.Set, java.util.Collection
    public int size() {
        return this.map.size();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean contains(Object o) {
        return this.map.get(o) == DUMP_VALUE;
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return this.map.keySet().iterator();
    }

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray() {
        return this.map.keySet().toArray();
    }

    @Override // java.util.Set, java.util.Collection
    public <T> T[] toArray(T[] a) {
        return (T[]) this.map.keySet().toArray(a);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean add(E o) {
        return this.map.put(o, DUMP_VALUE) == null;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean remove(Object o) {
        return this.map.remove(o) == DUMP_VALUE;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection<?> checkValues) {
        for (Object checkValue : checkValues) {
            if (!this.map.containsKey(checkValue)) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection<? extends E> additions) {
        boolean changed = false;
        for (E addition : additions) {
            changed = add(addition) || changed;
        }
        return changed;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection<?> keepers) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection<?> removals) {
        boolean changed = false;
        for (Object removal : removals) {
            changed = remove(removal) || changed;
        }
        return changed;
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        this.map.clear();
    }
}