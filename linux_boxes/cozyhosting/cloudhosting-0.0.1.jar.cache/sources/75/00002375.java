package org.aspectj.runtime.internal.cflowstack;

import java.util.Stack;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/runtime/internal/cflowstack/ThreadStack.class */
public interface ThreadStack {
    Stack getThreadStack();

    void removeThreadStack();
}