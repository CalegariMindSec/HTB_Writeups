package org.aspectj.weaver.reflect;

import java.lang.reflect.Member;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/weaver/reflect/ArgNameFinder.class */
public interface ArgNameFinder {
    String[] getParameterNames(Member member);
}