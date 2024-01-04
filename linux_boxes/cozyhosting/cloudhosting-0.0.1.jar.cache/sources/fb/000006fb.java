package com.sun.istack;

import java.util.ArrayList;
import java.util.Collection;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/istack-commons-runtime-4.1.1.jar:com/sun/istack/FinalArrayList.class */
public final class FinalArrayList<T> extends ArrayList<T> {
    private static final long serialVersionUID = -540534530037816397L;

    public FinalArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    public FinalArrayList() {
    }

    public FinalArrayList(Collection<? extends T> ts) {
        super(ts);
    }
}