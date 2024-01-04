package org.hibernate.internal.util.collections;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/BoundedConcurrentHashMap.class */
public class BoundedConcurrentHashMap<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    static final int MAXIMUM_CAPACITY = 1073741824;
    static final int MAX_SEGMENTS = 65536;
    static final int RETRIES_BEFORE_LOCK = 2;
    final int segmentMask;
    final int segmentShift;
    final Segment<K, V>[] segments;
    transient Set<K> keySet;
    transient Set<Map.Entry<K, V>> entrySet;
    transient Collection<V> values;

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/BoundedConcurrentHashMap$Eviction.class */
    public enum Eviction {
        LRU { // from class: org.hibernate.internal.util.collections.BoundedConcurrentHashMap.Eviction.1
            @Override // org.hibernate.internal.util.collections.BoundedConcurrentHashMap.Eviction
            public <K, V> EvictionPolicy<K, V> make(Segment<K, V> s, int capacity, float lf) {
                return new LRU(s, capacity, lf, capacity * 10, lf);
            }
        },
        LIRS { // from class: org.hibernate.internal.util.collections.BoundedConcurrentHashMap.Eviction.2
            @Override // org.hibernate.internal.util.collections.BoundedConcurrentHashMap.Eviction
            public <K, V> EvictionPolicy<K, V> make(Segment<K, V> s, int capacity, float lf) {
                return new LIRS(s, capacity, capacity * 10, lf);
            }
        };

        abstract <K, V> EvictionPolicy<K, V> make(Segment<K, V> segment, int i, float f);
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/BoundedConcurrentHashMap$EvictionPolicy.class */
    public interface EvictionPolicy<K, V> {
        public static final int MAX_BATCH_SIZE = 64;

        HashEntry<K, V> createNewEntry(K k, int i, HashEntry<K, V> hashEntry, V v);

        void execute();

        void onEntryMiss(HashEntry<K, V> hashEntry);

        boolean onEntryHit(HashEntry<K, V> hashEntry);

        void onEntryRemove(HashEntry<K, V> hashEntry);

        void clear();

        Eviction strategy();

        boolean thresholdExpired();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/BoundedConcurrentHashMap$Recency.class */
    public enum Recency {
        HIR_RESIDENT,
        LIR_RESIDENT,
        HIR_NONRESIDENT
    }

    private static int hash(int h) {
        int h2 = h + ((h << 15) ^ (-12931));
        int h3 = h2 ^ (h2 >>> 10);
        int h4 = h3 + (h3 << 3);
        int h5 = h4 ^ (h4 >>> 6);
        int h6 = h5 + (h5 << 2) + (h5 << 14);
        return h6 ^ (h6 >>> 16);
    }

    final Segment<K, V> segmentFor(int hash) {
        return this.segments[(hash >>> this.segmentShift) & this.segmentMask];
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/BoundedConcurrentHashMap$HashEntry.class */
    public static class HashEntry<K, V> {
        final K key;
        final int hash;
        volatile V value;
        final HashEntry<K, V> next;

        HashEntry(K key, int hash, HashEntry<K, V> next, V value) {
            this.key = key;
            this.hash = hash;
            this.next = next;
            this.value = value;
        }

        public int hashCode() {
            int result = (17 * 31) + this.hash;
            return (result * 31) + this.key.hashCode();
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null) {
                return false;
            }
            HashEntry<?, ?> other = (HashEntry) o;
            return this.hash == other.hash && this.key.equals(other.key);
        }

        static <K, V> HashEntry<K, V>[] newArray(int i) {
            return new HashEntry[i];
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/BoundedConcurrentHashMap$LRU.class */
    static final class LRU<K, V> extends LinkedHashMap<HashEntry<K, V>, V> implements EvictionPolicy<K, V> {
        private final ConcurrentLinkedQueue<HashEntry<K, V>> accessQueue;
        private final Segment<K, V> segment;
        private final int maxBatchQueueSize;
        private final int trimDownSize;
        private final float batchThresholdFactor;
        private final Set<HashEntry<K, V>> evicted;

        public LRU(Segment<K, V> s, int capacity, float lf, int maxBatchSize, float batchThresholdFactor) {
            super(capacity, lf, true);
            this.segment = s;
            this.trimDownSize = capacity;
            this.maxBatchQueueSize = maxBatchSize > 64 ? 64 : maxBatchSize;
            this.batchThresholdFactor = batchThresholdFactor;
            this.accessQueue = new ConcurrentLinkedQueue<>();
            this.evicted = new HashSet();
        }

        @Override // org.hibernate.internal.util.collections.BoundedConcurrentHashMap.EvictionPolicy
        public void execute() {
            Iterator<HashEntry<K, V>> it = this.accessQueue.iterator();
            while (it.hasNext()) {
                HashEntry<K, V> e = it.next();
                put(e, e.value);
            }
            this.accessQueue.clear();
            this.evicted.clear();
        }

        @Override // org.hibernate.internal.util.collections.BoundedConcurrentHashMap.EvictionPolicy
        public void onEntryMiss(HashEntry<K, V> e) {
            put(e, e.value);
            if (!this.evicted.isEmpty()) {
                this.evicted.clear();
            }
        }

        @Override // org.hibernate.internal.util.collections.BoundedConcurrentHashMap.EvictionPolicy
        public boolean onEntryHit(HashEntry<K, V> e) {
            this.accessQueue.add(e);
            return ((float) this.accessQueue.size()) >= ((float) this.maxBatchQueueSize) * this.batchThresholdFactor;
        }

        @Override // org.hibernate.internal.util.collections.BoundedConcurrentHashMap.EvictionPolicy
        public boolean thresholdExpired() {
            return this.accessQueue.size() >= this.maxBatchQueueSize;
        }

        @Override // org.hibernate.internal.util.collections.BoundedConcurrentHashMap.EvictionPolicy
        public void onEntryRemove(HashEntry<K, V> e) {
            remove(e);
            do {
            } while (this.accessQueue.remove(e));
        }

        @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map, org.hibernate.internal.util.collections.BoundedConcurrentHashMap.EvictionPolicy
        public void clear() {
            super.clear();
            this.accessQueue.clear();
        }

        @Override // org.hibernate.internal.util.collections.BoundedConcurrentHashMap.EvictionPolicy
        public Eviction strategy() {
            return Eviction.LRU;
        }

        protected boolean isAboveThreshold() {
            return size() > this.trimDownSize;
        }

        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<HashEntry<K, V>, V> eldest) {
            boolean aboveThreshold = isAboveThreshold();
            if (aboveThreshold) {
                HashEntry<K, V> evictedEntry = eldest.getKey();
                this.segment.remove(evictedEntry.key, evictedEntry.hash, null);
                this.evicted.add(evictedEntry);
            }
            return aboveThreshold;
        }

        @Override // org.hibernate.internal.util.collections.BoundedConcurrentHashMap.EvictionPolicy
        public HashEntry<K, V> createNewEntry(K key, int hash, HashEntry<K, V> next, V value) {
            return new HashEntry<>(key, hash, next, value);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/BoundedConcurrentHashMap$LIRSHashEntry.class */
    public static final class LIRSHashEntry<K, V> extends HashEntry<K, V> {
        private LIRSHashEntry<K, V> previousInStack;
        private LIRSHashEntry<K, V> nextInStack;
        private LIRSHashEntry<K, V> previousInQueue;
        private LIRSHashEntry<K, V> nextInQueue;
        volatile Recency state;
        LIRS<K, V> owner;

        LIRSHashEntry(LIRS<K, V> owner, K key, int hash, HashEntry<K, V> next, V value) {
            super(key, hash, next, value);
            this.owner = owner;
            this.state = Recency.HIR_RESIDENT;
            this.previousInStack = this;
            this.nextInStack = this;
            this.previousInQueue = this;
            this.nextInQueue = this;
        }

        @Override // org.hibernate.internal.util.collections.BoundedConcurrentHashMap.HashEntry
        public int hashCode() {
            int result = (17 * 31) + this.hash;
            return (result * 31) + this.key.hashCode();
        }

        @Override // org.hibernate.internal.util.collections.BoundedConcurrentHashMap.HashEntry
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null) {
                return false;
            }
            HashEntry<?, ?> other = (HashEntry) o;
            return this.hash == other.hash && this.key.equals(other.key);
        }

        public boolean inStack() {
            return this.nextInStack != null;
        }

        public boolean inQueue() {
            return this.nextInQueue != null;
        }

        public void hit(Set<HashEntry<K, V>> evicted) {
            switch (this.state) {
                case LIR_RESIDENT:
                    hotHit(evicted);
                    return;
                case HIR_RESIDENT:
                    coldHit(evicted);
                    return;
                case HIR_NONRESIDENT:
                    throw new IllegalStateException("Can't hit a non-resident entry");
                default:
                    throw new AssertionError("Hit with unknown status: " + this.state);
            }
        }

        private void hotHit(Set<HashEntry<K, V>> evicted) {
            boolean onBottom = this.owner.stackBottom() == this;
            moveToStackTop();
            if (onBottom) {
                this.owner.pruneStack(evicted);
            }
        }

        private void coldHit(Set<HashEntry<K, V>> evicted) {
            boolean inStack = inStack();
            moveToStackTop();
            if (inStack) {
                hot();
                removeFromQueue();
                this.owner.stackBottom().migrateToQueue();
                this.owner.pruneStack(evicted);
                return;
            }
            moveToQueueEnd();
        }

        private Set<HashEntry<K, V>> miss() {
            Set<HashEntry<K, V>> evicted = Collections.emptySet();
            if (((LIRS) this.owner).hotSize < ((LIRS) this.owner).maximumHotSize) {
                warmupMiss();
            } else {
                evicted = new HashSet<>();
                fullMiss(evicted);
            }
            ((LIRS) this.owner).size++;
            return evicted;
        }

        private void warmupMiss() {
            hot();
            moveToStackTop();
        }

        private void fullMiss(Set<HashEntry<K, V>> evicted) {
            if (((LIRS) this.owner).size >= ((LIRS) this.owner).maximumSize) {
                LIRSHashEntry<K, V> evictedNode = this.owner.queueFront();
                evicted.add(evictedNode);
            }
            boolean inStack = inStack();
            moveToStackTop();
            if (inStack) {
                hot();
                this.owner.stackBottom().migrateToQueue();
                this.owner.pruneStack(evicted);
                return;
            }
            cold();
        }

        private void hot() {
            if (this.state != Recency.LIR_RESIDENT) {
                ((LIRS) this.owner).hotSize++;
            }
            this.state = Recency.LIR_RESIDENT;
        }

        private void cold() {
            if (this.state == Recency.LIR_RESIDENT) {
                ((LIRS) this.owner).hotSize--;
            }
            this.state = Recency.HIR_RESIDENT;
            moveToQueueEnd();
        }

        private void nonResident() {
            switch (this.state) {
                case LIR_RESIDENT:
                    ((LIRS) this.owner).hotSize--;
                case HIR_RESIDENT:
                    ((LIRS) this.owner).size--;
                    break;
            }
            this.state = Recency.HIR_NONRESIDENT;
        }

        public boolean isResident() {
            return this.state != Recency.HIR_NONRESIDENT;
        }

        private void tempRemoveFromStack() {
            if (inStack()) {
                this.previousInStack.nextInStack = this.nextInStack;
                this.nextInStack.previousInStack = this.previousInStack;
            }
        }

        private void removeFromStack() {
            tempRemoveFromStack();
            this.previousInStack = null;
            this.nextInStack = null;
        }

        private void addToStackBefore(LIRSHashEntry<K, V> existingEntry) {
            this.previousInStack = existingEntry.previousInStack;
            this.nextInStack = existingEntry;
            this.previousInStack.nextInStack = this;
            this.nextInStack.previousInStack = this;
        }

        private void moveToStackTop() {
            tempRemoveFromStack();
            addToStackBefore(((LIRS) this.owner).header.nextInStack);
        }

        private void moveToStackBottom() {
            tempRemoveFromStack();
            addToStackBefore(((LIRS) this.owner).header);
        }

        private void tempRemoveFromQueue() {
            if (inQueue()) {
                this.previousInQueue.nextInQueue = this.nextInQueue;
                this.nextInQueue.previousInQueue = this.previousInQueue;
            }
        }

        private void removeFromQueue() {
            tempRemoveFromQueue();
            this.previousInQueue = null;
            this.nextInQueue = null;
        }

        private void addToQueueBefore(LIRSHashEntry<K, V> existingEntry) {
            this.previousInQueue = existingEntry.previousInQueue;
            this.nextInQueue = existingEntry;
            this.previousInQueue.nextInQueue = this;
            this.nextInQueue.previousInQueue = this;
        }

        private void moveToQueueEnd() {
            tempRemoveFromQueue();
            addToQueueBefore(((LIRS) this.owner).header);
        }

        private void migrateToQueue() {
            removeFromStack();
            cold();
        }

        private void migrateToStack() {
            removeFromQueue();
            if (!inStack()) {
                moveToStackBottom();
            }
            hot();
        }

        private void evict() {
            removeFromQueue();
            removeFromStack();
            nonResident();
            this.owner = null;
        }

        private V remove() {
            boolean wasHot = this.state == Recency.LIR_RESIDENT;
            V result = this.value;
            LIRSHashEntry<K, V> end = this.owner != null ? this.owner.queueEnd() : null;
            evict();
            if (wasHot && end != null) {
                end.migrateToStack();
            }
            return result;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/BoundedConcurrentHashMap$LIRS.class */
    public static final class LIRS<K, V> implements EvictionPolicy<K, V> {
        private static final float L_LIRS = 0.95f;
        private final Segment<K, V> segment;
        private final ConcurrentLinkedQueue<LIRSHashEntry<K, V>> accessQueue;
        private final int maxBatchQueueSize;
        private int size;
        private final float batchThresholdFactor;
        private final LIRSHashEntry<K, V> header = new LIRSHashEntry<>(null, null, 0, null, null);
        private final int maximumHotSize;
        private final int maximumSize;
        private int hotSize;

        public LIRS(Segment<K, V> s, int capacity, int maxBatchSize, float batchThresholdFactor) {
            this.segment = s;
            this.maximumSize = capacity;
            this.maximumHotSize = calculateLIRSize(capacity);
            this.maxBatchQueueSize = maxBatchSize > 64 ? 64 : maxBatchSize;
            this.batchThresholdFactor = batchThresholdFactor;
            this.accessQueue = new ConcurrentLinkedQueue<>();
        }

        private static int calculateLIRSize(int maximumSize) {
            int result = (int) (L_LIRS * maximumSize);
            return result == maximumSize ? maximumSize - 1 : result;
        }

        @Override // org.hibernate.internal.util.collections.BoundedConcurrentHashMap.EvictionPolicy
        public void execute() {
            Set<HashEntry<K, V>> evicted = new HashSet<>();
            try {
                Iterator<LIRSHashEntry<K, V>> it = this.accessQueue.iterator();
                while (it.hasNext()) {
                    LIRSHashEntry<K, V> e = it.next();
                    if (e.isResident()) {
                        e.hit(evicted);
                    }
                }
                removeFromSegment(evicted);
                this.accessQueue.clear();
            } catch (Throwable th) {
                this.accessQueue.clear();
                throw th;
            }
        }

        private void pruneStack(Set<HashEntry<K, V>> evicted) {
            LIRSHashEntry<K, V> stackBottom = stackBottom();
            while (true) {
                LIRSHashEntry<K, V> bottom = stackBottom;
                if (bottom != null && bottom.state != Recency.LIR_RESIDENT) {
                    bottom.removeFromStack();
                    if (bottom.state == Recency.HIR_NONRESIDENT) {
                        evicted.add(bottom);
                    }
                    stackBottom = stackBottom();
                } else {
                    return;
                }
            }
        }

        @Override // org.hibernate.internal.util.collections.BoundedConcurrentHashMap.EvictionPolicy
        public void onEntryMiss(HashEntry<K, V> en) {
            LIRSHashEntry<K, V> e = (LIRSHashEntry) en;
            Set<HashEntry<K, V>> evicted = e.miss();
            removeFromSegment(evicted);
        }

        private void removeFromSegment(Set<HashEntry<K, V>> evicted) {
            for (HashEntry<K, V> e : evicted) {
                ((LIRSHashEntry) e).evict();
                this.segment.remove(e.key, e.hash, null);
            }
        }

        @Override // org.hibernate.internal.util.collections.BoundedConcurrentHashMap.EvictionPolicy
        public boolean onEntryHit(HashEntry<K, V> e) {
            this.accessQueue.add((LIRSHashEntry) e);
            return ((float) this.accessQueue.size()) >= ((float) this.maxBatchQueueSize) * this.batchThresholdFactor;
        }

        @Override // org.hibernate.internal.util.collections.BoundedConcurrentHashMap.EvictionPolicy
        public boolean thresholdExpired() {
            return this.accessQueue.size() >= this.maxBatchQueueSize;
        }

        @Override // org.hibernate.internal.util.collections.BoundedConcurrentHashMap.EvictionPolicy
        public void onEntryRemove(HashEntry<K, V> e) {
            ((LIRSHashEntry) e).remove();
            do {
            } while (this.accessQueue.remove(e));
        }

        @Override // org.hibernate.internal.util.collections.BoundedConcurrentHashMap.EvictionPolicy
        public void clear() {
            this.accessQueue.clear();
        }

        @Override // org.hibernate.internal.util.collections.BoundedConcurrentHashMap.EvictionPolicy
        public Eviction strategy() {
            return Eviction.LIRS;
        }

        private LIRSHashEntry<K, V> stackBottom() {
            LIRSHashEntry<K, V> bottom = ((LIRSHashEntry) this.header).previousInStack;
            if (bottom == this.header) {
                return null;
            }
            return bottom;
        }

        private LIRSHashEntry<K, V> queueFront() {
            LIRSHashEntry<K, V> front = ((LIRSHashEntry) this.header).nextInQueue;
            if (front == this.header) {
                return null;
            }
            return front;
        }

        private LIRSHashEntry<K, V> queueEnd() {
            LIRSHashEntry<K, V> end = ((LIRSHashEntry) this.header).previousInQueue;
            if (end == this.header) {
                return null;
            }
            return end;
        }

        @Override // org.hibernate.internal.util.collections.BoundedConcurrentHashMap.EvictionPolicy
        public HashEntry<K, V> createNewEntry(K key, int hash, HashEntry<K, V> next, V value) {
            return new LIRSHashEntry(this, key, hash, next, value);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/BoundedConcurrentHashMap$Segment.class */
    public static final class Segment<K, V> extends ReentrantLock {
        private static final long serialVersionUID = 2249069246763182397L;
        volatile transient int count;
        transient int modCount;
        transient int threshold;
        volatile transient HashEntry<K, V>[] table;
        final float loadFactor;
        final int evictCap;
        final transient EvictionPolicy<K, V> eviction;

        Segment(int cap, int evictCap, float lf, Eviction es) {
            this.loadFactor = lf;
            this.evictCap = evictCap;
            this.eviction = es.make(this, evictCap, lf);
            setTable(HashEntry.newArray(cap));
        }

        static <K, V> Segment<K, V>[] newArray(int i) {
            return new Segment[i];
        }

        void setTable(HashEntry<K, V>[] newTable) {
            this.threshold = (int) (newTable.length * this.loadFactor);
            this.table = newTable;
        }

        HashEntry<K, V> getFirst(int hash) {
            HashEntry<K, V>[] tab = this.table;
            return tab[hash & (tab.length - 1)];
        }

        V readValueUnderLock(HashEntry<K, V> e) {
            lock();
            try {
                return e.value;
            } finally {
                unlock();
            }
        }

        V get(Object key, int hash) {
            HashEntry<K, V> e;
            int c = this.count;
            if (c != 0) {
                V result = null;
                HashEntry<K, V> first = getFirst(hash);
                while (true) {
                    e = first;
                    if (e == null) {
                        break;
                    } else if (e.hash == hash && key.equals(e.key)) {
                        V v = e.value;
                        if (v != null) {
                            result = v;
                        } else {
                            result = readValueUnderLock(e);
                        }
                    } else {
                        first = e.next;
                    }
                }
                if (result != null && this.eviction.onEntryHit(e)) {
                    attemptEviction(false);
                }
                return result;
            }
            return null;
        }

        boolean containsKey(Object key, int hash) {
            if (this.count != 0) {
                HashEntry<K, V> first = getFirst(hash);
                while (true) {
                    HashEntry<K, V> e = first;
                    if (e != null) {
                        if (e.hash == hash && key.equals(e.key)) {
                            return true;
                        }
                        first = e.next;
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
            r7 = r7 + 1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        boolean containsValue(java.lang.Object r4) {
            /*
                r3 = this;
                r0 = r3
                int r0 = r0.count
                if (r0 == 0) goto L52
                r0 = r3
                org.hibernate.internal.util.collections.BoundedConcurrentHashMap$HashEntry<K, V>[] r0 = r0.table
                r5 = r0
                r0 = r5
                int r0 = r0.length
                r6 = r0
                r0 = 0
                r7 = r0
            L12:
                r0 = r7
                r1 = r6
                if (r0 >= r1) goto L52
                r0 = r5
                r1 = r7
                r0 = r0[r1]
                r8 = r0
            L1e:
                r0 = r8
                if (r0 == 0) goto L4c
                r0 = r8
                V r0 = r0.value
                r9 = r0
                r0 = r9
                if (r0 != 0) goto L37
                r0 = r3
                r1 = r8
                java.lang.Object r0 = r0.readValueUnderLock(r1)
                r9 = r0
            L37:
                r0 = r4
                r1 = r9
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L42
                r0 = 1
                return r0
            L42:
                r0 = r8
                org.hibernate.internal.util.collections.BoundedConcurrentHashMap$HashEntry<K, V> r0 = r0.next
                r8 = r0
                goto L1e
            L4c:
                int r7 = r7 + 1
                goto L12
            L52:
                r0 = 0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.hibernate.internal.util.collections.BoundedConcurrentHashMap.Segment.containsValue(java.lang.Object):boolean");
        }

        boolean replace(K key, int hash, V oldValue, V newValue) {
            lock();
            try {
                HashEntry<K, V> e = getFirst(hash);
                while (e != null && (e.hash != hash || !key.equals(e.key))) {
                    e = e.next;
                }
                boolean replaced = false;
                if (e != null && oldValue.equals(e.value)) {
                    replaced = true;
                    e.value = newValue;
                    if (this.eviction.onEntryHit(e)) {
                        attemptEviction(true);
                    }
                }
                return replaced;
            } finally {
                unlock();
            }
        }

        V replace(K key, int hash, V newValue) {
            lock();
            try {
                HashEntry<K, V> e = getFirst(hash);
                while (e != null && (e.hash != hash || !key.equals(e.key))) {
                    e = e.next;
                }
                V oldValue = null;
                if (e != null) {
                    oldValue = e.value;
                    e.value = newValue;
                    if (this.eviction.onEntryHit(e)) {
                        attemptEviction(true);
                    }
                }
                return oldValue;
            } finally {
                unlock();
            }
        }

        V put(K key, int hash, V value, boolean onlyIfAbsent) {
            V oldValue;
            lock();
            try {
                int c = this.count + 1;
                HashEntry<K, V>[] tab = this.table;
                int index = hash & (tab.length - 1);
                HashEntry<K, V> first = tab[index];
                HashEntry<K, V> e = first;
                while (e != null && (e.hash != hash || !key.equals(e.key))) {
                    e = e.next;
                }
                if (e != null) {
                    oldValue = e.value;
                    if (!onlyIfAbsent) {
                        e.value = value;
                        this.eviction.onEntryHit(e);
                    }
                } else {
                    oldValue = null;
                    this.modCount++;
                    this.count = c;
                    if (c > this.evictCap) {
                        this.eviction.execute();
                        first = tab[index];
                    }
                    tab[index] = this.eviction.createNewEntry(key, hash, first, value);
                    this.eviction.onEntryMiss(tab[index]);
                }
                return oldValue;
            } finally {
                unlock();
            }
        }

        void rehash() {
            HashEntry<K, V>[] oldTable = this.table;
            int oldCapacity = oldTable.length;
            if (oldCapacity >= 1073741824) {
                return;
            }
            HashEntry<K, V>[] newTable = HashEntry.newArray(oldCapacity << 1);
            this.threshold = (int) (newTable.length * this.loadFactor);
            int sizeMask = newTable.length - 1;
            for (HashEntry<K, V> e : oldTable) {
                if (e != null) {
                    HashEntry<K, V> next = e.next;
                    int idx = e.hash & sizeMask;
                    if (next == null) {
                        newTable[idx] = e;
                    } else {
                        HashEntry<K, V> lastRun = e;
                        int lastIdx = idx;
                        HashEntry<K, V> hashEntry = next;
                        while (true) {
                            HashEntry<K, V> last = hashEntry;
                            if (last == null) {
                                break;
                            }
                            int k = last.hash & sizeMask;
                            if (k != lastIdx) {
                                lastIdx = k;
                                lastRun = last;
                            }
                            hashEntry = last.next;
                        }
                        newTable[lastIdx] = lastRun;
                        HashEntry<K, V> hashEntry2 = e;
                        while (true) {
                            HashEntry<K, V> p = hashEntry2;
                            if (p != lastRun) {
                                int k2 = p.hash & sizeMask;
                                HashEntry<K, V> n = newTable[k2];
                                newTable[k2] = this.eviction.createNewEntry(p.key, p.hash, n, p.value);
                                hashEntry2 = p.next;
                            }
                        }
                    }
                }
            }
            this.table = newTable;
        }

        V remove(Object key, int hash, Object value) {
            lock();
            try {
                int c = this.count - 1;
                HashEntry<K, V>[] tab = this.table;
                int index = hash & (tab.length - 1);
                HashEntry<K, V> first = tab[index];
                HashEntry<K, V> e = first;
                while (e != null && (e.hash != hash || !key.equals(e.key))) {
                    e = e.next;
                }
                V oldValue = null;
                if (e != null) {
                    V v = e.value;
                    if (value == null || value.equals(v)) {
                        oldValue = v;
                        this.modCount++;
                        this.eviction.onEntryRemove(e);
                        HashEntry<K, V> newFirst = e.next;
                        for (HashEntry<K, V> p = first; p != e; p = p.next) {
                            this.eviction.onEntryRemove(p);
                            newFirst = this.eviction.createNewEntry(p.key, p.hash, newFirst, p.value);
                            this.eviction.onEntryMiss(newFirst);
                        }
                        tab[index] = newFirst;
                        this.count = c;
                    }
                }
                return oldValue;
            } finally {
                unlock();
            }
        }

        void clear() {
            if (this.count != 0) {
                lock();
                try {
                    HashEntry<K, V>[] tab = this.table;
                    Arrays.fill(tab, (Object) null);
                    this.modCount++;
                    this.eviction.clear();
                    this.count = 0;
                } finally {
                    unlock();
                }
            }
        }

        private void attemptEviction(boolean lockedAlready) {
            boolean obtainedLock = lockedAlready || tryLock();
            if (!obtainedLock && this.eviction.thresholdExpired()) {
                lock();
                obtainedLock = true;
            }
            if (obtainedLock) {
                try {
                    if (this.eviction.thresholdExpired()) {
                        this.eviction.execute();
                    }
                } finally {
                    if (!lockedAlready) {
                        unlock();
                    }
                }
            }
        }
    }

    public BoundedConcurrentHashMap(int capacity, int concurrencyLevel, Eviction evictionStrategy) {
        int ssize;
        int cap;
        if (capacity < 0 || concurrencyLevel <= 0) {
            throw new IllegalArgumentException();
        }
        int concurrencyLevel2 = Math.max(Math.min(capacity / 2, concurrencyLevel), 1);
        if (capacity < concurrencyLevel2 * 2 && capacity != 1) {
            throw new IllegalArgumentException("Maximum capacity has to be at least twice the concurrencyLevel");
        }
        if (evictionStrategy == null) {
            throw new IllegalArgumentException();
        }
        int sshift = 0;
        int i = 1;
        while (true) {
            ssize = i;
            if (ssize >= (concurrencyLevel2 > 65536 ? 65536 : concurrencyLevel2)) {
                break;
            }
            sshift++;
            i = ssize << 1;
        }
        this.segmentShift = 32 - sshift;
        this.segmentMask = ssize - 1;
        this.segments = Segment.newArray(ssize);
        int c = (capacity > 1073741824 ? 1073741824 : capacity) / ssize;
        int i2 = 1;
        while (true) {
            cap = i2;
            if (cap >= c) {
                break;
            }
            i2 = cap << 1;
        }
        for (int i3 = 0; i3 < this.segments.length; i3++) {
            this.segments[i3] = new Segment<>(cap, c, 0.75f, evictionStrategy);
        }
    }

    public BoundedConcurrentHashMap(int capacity, int concurrencyLevel) {
        this(capacity, concurrencyLevel, Eviction.LRU);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        Segment<K, V>[] segments = this.segments;
        int[] mc = new int[segments.length];
        int mcsum = 0;
        for (int i = 0; i < segments.length; i++) {
            if (segments[i].count != 0) {
                return false;
            }
            int i2 = segments[i].modCount;
            mc[i] = i2;
            mcsum += i2;
        }
        if (mcsum != 0) {
            for (int i3 = 0; i3 < segments.length; i3++) {
                if (segments[i3].count != 0 || mc[i3] != segments[i3].modCount) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        Segment<K, V>[] segments = this.segments;
        long sum = 0;
        long check = 0;
        int[] mc = new int[segments.length];
        for (int k = 0; k < 2; k++) {
            check = 0;
            sum = 0;
            int mcsum = 0;
            for (int i = 0; i < segments.length; i++) {
                sum += segments[i].count;
                int i2 = segments[i].modCount;
                mc[i] = i2;
                mcsum += i2;
            }
            if (mcsum != 0) {
                int i3 = 0;
                while (true) {
                    if (i3 >= segments.length) {
                        break;
                    }
                    check += segments[i3].count;
                    if (mc[i3] != segments[i3].modCount) {
                        check = -1;
                        break;
                    }
                    i3++;
                }
            }
            if (check == sum) {
                break;
            }
        }
        if (check != sum) {
            sum = 0;
            for (Segment<K, V> segment : segments) {
                segment.lock();
            }
            for (Segment<K, V> segment2 : segments) {
                try {
                    sum += segment2.count;
                } finally {
                    for (Segment<K, V> segment3 : segments) {
                        segment3.unlock();
                    }
                }
            }
        }
        if (sum > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) sum;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object key) {
        int hash = hash(key.hashCode());
        return segmentFor(hash).get(key, hash);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object key) {
        int hash = hash(key.hashCode());
        return segmentFor(hash).containsKey(key, hash);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object value) {
        if (value == null) {
            throw new NullPointerException();
        }
        Segment<K, V>[] segments = this.segments;
        int[] mc = new int[segments.length];
        for (int k = 0; k < 2; k++) {
            int mcsum = 0;
            for (int i = 0; i < segments.length; i++) {
                int i2 = segments[i].count;
                int i3 = segments[i].modCount;
                mc[i] = i3;
                mcsum += i3;
                if (segments[i].containsValue(value)) {
                    return true;
                }
            }
            boolean cleanSweep = true;
            if (mcsum != 0) {
                int i4 = 0;
                while (true) {
                    if (i4 >= segments.length) {
                        break;
                    }
                    int i5 = segments[i4].count;
                    if (mc[i4] != segments[i4].modCount) {
                        cleanSweep = false;
                        break;
                    }
                    i4++;
                }
            }
            if (cleanSweep) {
                return false;
            }
        }
        for (Segment<K, V> segment : segments) {
            segment.lock();
        }
        boolean found = false;
        int i6 = 0;
        while (true) {
            try {
                if (i6 >= segments.length) {
                    break;
                } else if (segments[i6].containsValue(value)) {
                    found = true;
                    break;
                } else {
                    i6++;
                }
            } finally {
                for (Segment<K, V> segment2 : segments) {
                    segment2.unlock();
                }
            }
        }
        return found;
    }

    public boolean contains(Object value) {
        return containsValue(value);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K key, V value) {
        if (value == null) {
            throw new NullPointerException();
        }
        int hash = hash(key.hashCode());
        return segmentFor(hash).put(key, hash, value, false);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V putIfAbsent(K key, V value) {
        if (value == null) {
            throw new NullPointerException();
        }
        int hash = hash(key.hashCode());
        return segmentFor(hash).put(key, hash, value, true);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object key) {
        int hash = hash(key.hashCode());
        return segmentFor(hash).remove(key, hash, null);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean remove(Object key, Object value) {
        int hash = hash(key.hashCode());
        return (value == null || segmentFor(hash).remove(key, hash, value) == null) ? false : true;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean replace(K key, V oldValue, V newValue) {
        if (oldValue == null || newValue == null) {
            throw new NullPointerException();
        }
        int hash = hash(key.hashCode());
        return segmentFor(hash).replace(key, hash, oldValue, newValue);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V replace(K key, V value) {
        if (value == null) {
            throw new NullPointerException();
        }
        int hash = hash(key.hashCode());
        return segmentFor(hash).replace(key, hash, value);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        for (int i = 0; i < this.segments.length; i++) {
            this.segments[i].clear();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> ks = this.keySet;
        if (ks != null) {
            return ks;
        }
        KeySet keySet = new KeySet();
        this.keySet = keySet;
        return keySet;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        Collection<V> vs = this.values;
        if (vs != null) {
            return vs;
        }
        Values values = new Values();
        this.values = values;
        return values;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> es = this.entrySet;
        if (es != null) {
            return es;
        }
        EntrySet entrySet = new EntrySet();
        this.entrySet = entrySet;
        return entrySet;
    }

    public Enumeration<K> keys() {
        return new KeyIterator();
    }

    public Enumeration<V> elements() {
        return new ValueIterator();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/BoundedConcurrentHashMap$HashIterator.class */
    public abstract class HashIterator {
        int nextSegmentIndex;
        int nextTableIndex = -1;
        HashEntry<K, V>[] currentTable;
        HashEntry<K, V> nextEntry;
        HashEntry<K, V> lastReturned;

        HashIterator() {
            this.nextSegmentIndex = BoundedConcurrentHashMap.this.segments.length - 1;
            advance();
        }

        public boolean hasMoreElements() {
            return hasNext();
        }

        final void advance() {
            if (this.nextEntry != null) {
                HashEntry<K, V> hashEntry = this.nextEntry.next;
                this.nextEntry = hashEntry;
                if (hashEntry != null) {
                    return;
                }
            }
            while (this.nextTableIndex >= 0) {
                HashEntry<K, V>[] hashEntryArr = this.currentTable;
                int i = this.nextTableIndex;
                this.nextTableIndex = i - 1;
                HashEntry<K, V> hashEntry2 = hashEntryArr[i];
                this.nextEntry = hashEntry2;
                if (hashEntry2 != null) {
                    return;
                }
            }
            while (this.nextSegmentIndex >= 0) {
                Segment<K, V>[] segmentArr = BoundedConcurrentHashMap.this.segments;
                int i2 = this.nextSegmentIndex;
                this.nextSegmentIndex = i2 - 1;
                Segment<K, V> seg = segmentArr[i2];
                if (seg.count != 0) {
                    this.currentTable = seg.table;
                    for (int j = this.currentTable.length - 1; j >= 0; j--) {
                        HashEntry<K, V> hashEntry3 = this.currentTable[j];
                        this.nextEntry = hashEntry3;
                        if (hashEntry3 != null) {
                            this.nextTableIndex = j - 1;
                            return;
                        }
                    }
                    continue;
                }
            }
        }

        public boolean hasNext() {
            return this.nextEntry != null;
        }

        HashEntry<K, V> nextEntry() {
            if (this.nextEntry == null) {
                throw new NoSuchElementException();
            }
            this.lastReturned = this.nextEntry;
            advance();
            return this.lastReturned;
        }

        public void remove() {
            if (this.lastReturned == null) {
                throw new IllegalStateException();
            }
            BoundedConcurrentHashMap.this.remove(this.lastReturned.key);
            this.lastReturned = null;
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/BoundedConcurrentHashMap$KeyIterator.class */
    final class KeyIterator extends BoundedConcurrentHashMap<K, V>.HashIterator implements Iterator<K>, Enumeration<K> {
        KeyIterator() {
            super();
        }

        @Override // java.util.Iterator
        public K next() {
            return super.nextEntry().key;
        }

        @Override // java.util.Enumeration
        public K nextElement() {
            return super.nextEntry().key;
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/BoundedConcurrentHashMap$ValueIterator.class */
    final class ValueIterator extends BoundedConcurrentHashMap<K, V>.HashIterator implements Iterator<V>, Enumeration<V> {
        ValueIterator() {
            super();
        }

        @Override // java.util.Iterator
        public V next() {
            return super.nextEntry().value;
        }

        @Override // java.util.Enumeration
        public V nextElement() {
            return super.nextEntry().value;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/BoundedConcurrentHashMap$WriteThroughEntry.class */
    public final class WriteThroughEntry extends AbstractMap.SimpleEntry<K, V> {
        private static final long serialVersionUID = -7041346694785573824L;

        WriteThroughEntry(K k, V v) {
            super(k, v);
        }

        @Override // java.util.AbstractMap.SimpleEntry, java.util.Map.Entry
        public V setValue(V value) {
            if (value == null) {
                throw new NullPointerException();
            }
            V v = (V) super.setValue(value);
            BoundedConcurrentHashMap.this.put(getKey(), value);
            return v;
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/BoundedConcurrentHashMap$EntryIterator.class */
    final class EntryIterator extends BoundedConcurrentHashMap<K, V>.HashIterator implements Iterator<Map.Entry<K, V>> {
        EntryIterator() {
            super();
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            HashEntry<K, V> e = super.nextEntry();
            return new WriteThroughEntry(e.key, e.value);
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/BoundedConcurrentHashMap$KeySet.class */
    final class KeySet extends AbstractSet<K> {
        KeySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return BoundedConcurrentHashMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return BoundedConcurrentHashMap.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            return BoundedConcurrentHashMap.this.containsKey(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            return BoundedConcurrentHashMap.this.remove(o) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            BoundedConcurrentHashMap.this.clear();
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/BoundedConcurrentHashMap$Values.class */
    final class Values extends AbstractCollection<V> {
        Values() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return BoundedConcurrentHashMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return BoundedConcurrentHashMap.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object o) {
            return BoundedConcurrentHashMap.this.containsValue(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            BoundedConcurrentHashMap.this.clear();
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/BoundedConcurrentHashMap$EntrySet.class */
    final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> e = (Map.Entry) o;
            Object obj = BoundedConcurrentHashMap.this.get(e.getKey());
            return obj != null && obj.equals(e.getValue());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> e = (Map.Entry) o;
            return BoundedConcurrentHashMap.this.remove(e.getKey(), e.getValue());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return BoundedConcurrentHashMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return BoundedConcurrentHashMap.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            BoundedConcurrentHashMap.this.clear();
        }
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        for (int k = 0; k < this.segments.length; k++) {
            Segment<K, V> seg = this.segments[k];
            seg.lock();
            try {
                HashEntry<K, V>[] tab = seg.table;
                for (int i = 0; i < tab.length; i++) {
                    for (HashEntry<K, V> e = tab[i]; e != null; e = e.next) {
                        s.writeObject(e.key);
                        s.writeObject(e.value);
                    }
                }
            } finally {
                seg.unlock();
            }
        }
        s.writeObject(null);
        s.writeObject(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        for (int i = 0; i < this.segments.length; i++) {
            this.segments[i].setTable(new HashEntry[1]);
        }
        while (true) {
            Object readObject = s.readObject();
            Object readObject2 = s.readObject();
            if (readObject != null) {
                put(readObject, readObject2);
            } else {
                return;
            }
        }
    }
}