package org.jboss.jandex;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.opt.Const;
import org.springframework.util.ClassUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jandex-2.4.2.Final.jar:org/jboss/jandex/StrongInternPool.class */
public class StrongInternPool<E> implements Cloneable, Serializable {
    private static final Object NULL = new Object();
    private static final long serialVersionUID = 10929568968762L;
    private static final int DEFAULT_CAPACITY = 8;
    private static final int MAXIMUM_CAPACITY = 1073741824;
    private static final float DEFAULT_LOAD_FACTOR = 0.67f;
    private transient Object[] table;
    private transient int size;
    private transient int threshold;
    private final float loadFactor;
    private transient int modCount;
    private transient StrongInternPool<E>.Index index;

    static /* synthetic */ int access$610(StrongInternPool x0) {
        int i = x0.size;
        x0.size = i - 1;
        return i;
    }

    public StrongInternPool(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Can not have a negative size table!");
        }
        initialCapacity = initialCapacity > 1073741824 ? 1073741824 : initialCapacity;
        if (loadFactor <= Const.default_value_float || loadFactor > 1.0f) {
            throw new IllegalArgumentException("Load factor must be greater than 0 and less than or equal to 1");
        }
        this.loadFactor = loadFactor;
        init(initialCapacity, loadFactor);
    }

    private void init(int initialCapacity, float loadFactor) {
        int c;
        int i = 1;
        while (true) {
            c = i;
            if (c >= initialCapacity) {
                break;
            }
            i = c << 1;
        }
        this.threshold = (int) (c * loadFactor);
        if (initialCapacity > this.threshold && c < 1073741824) {
            c <<= 1;
            this.threshold = (int) (c * loadFactor);
        }
        this.table = new Object[c];
    }

    private static boolean eq(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        }
        if ((o1 instanceof Object[]) && (o2 instanceof Object[])) {
            return Arrays.equals((Object[]) o1, (Object[]) o2);
        }
        if ((o1 instanceof byte[]) && (o2 instanceof byte[])) {
            return Arrays.equals((byte[]) o1, (byte[]) o2);
        }
        return o1 != null && o1.equals(o2);
    }

    public StrongInternPool(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public StrongInternPool() {
        this(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int hash(Object o) {
        int h = o instanceof Object[] ? Arrays.hashCode((Object[]) o) : o instanceof byte[] ? Arrays.hashCode((byte[]) o) : o.hashCode();
        return (h << 1) - (h << 8);
    }

    private static final <K> K maskNull(K key) {
        return key == null ? (K) NULL : key;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <K> K unmaskNull(K key) {
        if (key == NULL) {
            return null;
        }
        return key;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int nextIndex(int index, int length) {
        return index >= length - 1 ? 0 : index + 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int index(int hashCode, int length) {
        return hashCode & (length - 1);
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean contains(Object entry) {
        Object entry2 = maskNull(entry);
        int hash = hash(entry2);
        int length = this.table.length;
        int index = index(hash, length);
        do {
            Object e = this.table[index];
            if (e == null) {
                return false;
            }
            if (eq(entry2, e)) {
                return true;
            }
            index = nextIndex(index, length);
        } while (index != index);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int offset(Object entry) {
        Object entry2 = maskNull(entry);
        int hash = hash(entry2);
        int length = this.table.length;
        int index = index(hash, length);
        do {
            Object e = this.table[index];
            if (e == null) {
                return -1;
            }
            if (eq(entry2, e)) {
                return index;
            }
            index = nextIndex(index, length);
        } while (index != index);
        return -1;
    }

    public E intern(E entry) {
        Object maskNull = maskNull(entry);
        Object[] table = this.table;
        int hash = hash(maskNull);
        int length = table.length;
        int index = index(hash, length);
        do {
            Object e = table[index];
            if (e != null) {
                if (eq(maskNull, e)) {
                    return (E) unmaskNull(e);
                }
                index = nextIndex(index, length);
            } else {
                this.modCount++;
                table[index] = maskNull;
                int i = this.size + 1;
                this.size = i;
                if (i >= this.threshold) {
                    resize(length);
                }
                return (E) unmaskNull(maskNull);
            }
        } while (index != index);
        throw new IllegalStateException("Table is full!");
    }

    private void resize(int from) {
        int index;
        int newLength = from << 1;
        if (newLength > 1073741824 || newLength <= from) {
            return;
        }
        Object[] newTable = new Object[newLength];
        Object[] old = this.table;
        for (Object e : old) {
            if (e != null) {
                int index2 = index(hash(e), newLength);
                while (true) {
                    index = index2;
                    if (newTable[index] == null) {
                        break;
                    }
                    index2 = nextIndex(index, newLength);
                }
                newTable[index] = e;
            }
        }
        this.threshold = (int) (this.loadFactor * newLength);
        this.table = newTable;
    }

    public boolean remove(Object o) {
        Object o2 = maskNull(o);
        Object[] table = this.table;
        int length = table.length;
        int hash = hash(o2);
        int start = index(hash, length);
        int index = start;
        do {
            Object e = table[index];
            if (e == null) {
                return false;
            }
            if (eq(e, o2)) {
                table[index] = null;
                relocate(index);
                this.modCount++;
                this.size--;
                return true;
            }
            index = nextIndex(index, length);
        } while (index != start);
        return false;
    }

    private void relocate(int start) {
        Object[] table = this.table;
        int length = table.length;
        int nextIndex = nextIndex(start, length);
        while (true) {
            int current = nextIndex;
            Object e = table[current];
            if (e == null) {
                return;
            }
            int prefer = index(hash(e), length);
            if ((current < prefer && (prefer <= start || start <= current)) || (prefer <= start && start <= current)) {
                table[start] = e;
                table[current] = null;
                start = current;
            }
            nextIndex = nextIndex(current, length);
        }
    }

    public void clear() {
        this.modCount++;
        Object[] table = this.table;
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        this.size = 0;
    }

    /* renamed from: clone */
    public StrongInternPool<E> m3833clone() {
        try {
            StrongInternPool<E> clone = (StrongInternPool) super.clone();
            clone.table = (Object[]) this.table.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException(e);
        }
    }

    public Object[] toInternalArray() {
        return this.table;
    }

    public void printDebugStats() {
        int optimal = 0;
        int total = 0;
        int totalSkew = 0;
        int maxSkew = 0;
        for (int i = 0; i < this.table.length; i++) {
            Object e = this.table[i];
            if (e != null) {
                total++;
                int target = index(hash(e), this.table.length);
                if (i == target) {
                    optimal++;
                } else {
                    int skew = Math.abs(i - target);
                    if (skew > maxSkew) {
                        maxSkew = skew;
                    }
                    totalSkew += skew;
                }
            }
        }
        System.out.println(" Size:            " + this.size);
        System.out.println(" Real Size:       " + total);
        System.out.println(" Optimal:         " + optimal + " (" + ((optimal * 100.0f) / total) + "%)");
        System.out.println(" Average Distnce: " + (totalSkew / (total - optimal)));
        System.out.println(" Max Distance:    " + maxSkew);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        int size = s.readInt();
        init(size, this.loadFactor);
        for (int i = 0; i < size; i++) {
            putForCreate(s.readObject());
        }
        this.size = size;
    }

    /* JADX WARN: Incorrect condition in loop: B:4:0x0023 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void putForCreate(E r5) {
        /*
            r4 = this;
            r0 = r5
            java.lang.Object r0 = maskNull(r0)
            r5 = r0
            r0 = r4
            java.lang.Object[] r0 = r0.table
            r6 = r0
            r0 = r5
            int r0 = hash(r0)
            r7 = r0
            r0 = r6
            int r0 = r0.length
            r8 = r0
            r0 = r7
            r1 = r8
            int r0 = index(r0, r1)
            r9 = r0
            r0 = r6
            r1 = r9
            r0 = r0[r1]
            r10 = r0
        L21:
            r0 = r10
            if (r0 == 0) goto L39
            r0 = r4
            r1 = r9
            r2 = r8
            int r0 = r0.nextIndex(r1, r2)
            r9 = r0
            r0 = r6
            r1 = r9
            r0 = r0[r1]
            r10 = r0
            goto L21
        L39:
            r0 = r6
            r1 = r9
            r2 = r5
            r0[r1] = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jboss.jandex.StrongInternPool.putForCreate(java.lang.Object):void");
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        Object[] objArr;
        s.defaultWriteObject();
        s.writeInt(this.size);
        for (Object e : this.table) {
            if (e != null) {
                s.writeObject(unmaskNull(e));
            }
        }
    }

    public Iterator<E> iterator() {
        return new IdentityHashSetIterator();
    }

    public StrongInternPool<E>.Index index() {
        if (this.index == null || ((Index) this.index).modCount != this.modCount) {
            this.index = new Index();
        }
        return this.index;
    }

    public String toString() {
        Iterator<E> i = iterator();
        if (!i.hasNext()) {
            return ClassUtils.ARRAY_SUFFIX;
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        while (true) {
            E e = i.next();
            sb.append(e);
            if (!i.hasNext()) {
                return sb.append(']').toString();
            }
            sb.append(", ");
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jandex-2.4.2.Final.jar:org/jboss/jandex/StrongInternPool$Index.class */
    public class Index {
        private int[] offsets;
        private int modCount;

        Index() {
            this.offsets = new int[StrongInternPool.this.table.length];
            int c = 1;
            for (int i = 0; i < this.offsets.length; i++) {
                if (StrongInternPool.this.table[i] != null) {
                    int i2 = c;
                    c++;
                    this.offsets[i] = i2;
                }
            }
            this.modCount = StrongInternPool.this.modCount;
        }

        public int positionOf(E e) {
            int offset = StrongInternPool.this.offset(e);
            if (offset < 0) {
                return -1;
            }
            return this.offsets[offset];
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jandex-2.4.2.Final.jar:org/jboss/jandex/StrongInternPool$IdentityHashSetIterator.class */
    public class IdentityHashSetIterator implements Iterator<E> {
        private int next;
        private int expectedCount;
        private int current;
        private boolean hasNext;
        Object[] table;

        private IdentityHashSetIterator() {
            this.next = 0;
            this.expectedCount = StrongInternPool.this.modCount;
            this.current = -1;
            this.table = StrongInternPool.this.table;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.hasNext) {
                return true;
            }
            Object[] table = this.table;
            for (int i = this.next; i < table.length; i++) {
                if (table[i] != null) {
                    this.next = i;
                    this.hasNext = true;
                    return true;
                }
            }
            this.next = table.length;
            return false;
        }

        @Override // java.util.Iterator
        public E next() {
            if (StrongInternPool.this.modCount != this.expectedCount) {
                throw new ConcurrentModificationException();
            }
            if (!this.hasNext && !hasNext()) {
                throw new NoSuchElementException();
            }
            int i = this.next;
            this.next = i + 1;
            this.current = i;
            this.hasNext = false;
            return (E) StrongInternPool.unmaskNull(this.table[this.current]);
        }

        @Override // java.util.Iterator
        public void remove() {
            if (StrongInternPool.this.modCount != this.expectedCount) {
                throw new ConcurrentModificationException();
            }
            int current = this.current;
            int delete = current;
            if (current == -1) {
                throw new IllegalStateException();
            }
            this.current = -1;
            this.next = delete;
            Object[] table = this.table;
            if (table != StrongInternPool.this.table) {
                StrongInternPool.this.remove(table[delete]);
                table[delete] = null;
                this.expectedCount = StrongInternPool.this.modCount;
                return;
            }
            int length = table.length;
            int i = delete;
            table[delete] = null;
            StrongInternPool.access$610(StrongInternPool.this);
            while (true) {
                i = StrongInternPool.this.nextIndex(i, length);
                Object e = table[i];
                if (e != null) {
                    int prefer = StrongInternPool.index(StrongInternPool.hash(e), length);
                    if ((i < prefer && (prefer <= delete || delete <= i)) || (prefer <= delete && delete <= i)) {
                        if (i < current && current <= delete && table == StrongInternPool.this.table) {
                            int remaining = length - current;
                            Object[] newTable = new Object[remaining];
                            System.arraycopy(table, current, newTable, 0, remaining);
                            this.table = newTable;
                            this.next = 0;
                        }
                        table[delete] = e;
                        table[i] = null;
                        delete = i;
                    }
                } else {
                    return;
                }
            }
        }
    }
}