package org.aspectj.asm;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/asm/IModelFilter.class */
public interface IModelFilter {
    String processFilelocation(String str);

    boolean wantsHandleIds();
}