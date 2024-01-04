package com.fasterxml.jackson.databind.util.internal;

import com.fasterxml.jackson.databind.util.internal.Linked;

/* compiled from: LinkedDeque.java */
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jackson-databind-2.14.1.jar:com/fasterxml/jackson/databind/util/internal/Linked.class */
interface Linked<T extends Linked<T>> {
    T getPrevious();

    void setPrevious(T t);

    T getNext();

    void setNext(T t);
}