package org.hibernate.bytecode.enhance.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/bytecode/enhance/spi/CollectionTracker.class */
public interface CollectionTracker {
    void add(String str, int i);

    int getSize(String str);
}