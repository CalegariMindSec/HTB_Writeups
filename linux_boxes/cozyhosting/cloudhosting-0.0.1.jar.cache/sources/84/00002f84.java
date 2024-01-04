package org.hibernate.bytecode.enhance.internal.tracker;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/bytecode/enhance/internal/tracker/DirtyTracker.class */
public interface DirtyTracker {
    void add(String str);

    boolean contains(String str);

    void clear();

    boolean isEmpty();

    String[] get();

    void suspend(boolean z);
}