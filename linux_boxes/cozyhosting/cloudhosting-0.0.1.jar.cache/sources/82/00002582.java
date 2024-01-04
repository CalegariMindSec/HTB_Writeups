package org.aspectj.weaver.tools;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/weaver/tools/MatchingContext.class */
public interface MatchingContext {
    boolean hasContextBinding(String str);

    Object getBinding(String str);
}