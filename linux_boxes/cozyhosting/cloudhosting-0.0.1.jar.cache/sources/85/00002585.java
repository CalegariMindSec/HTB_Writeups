package org.aspectj.weaver.tools;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/weaver/tools/PointcutParameter.class */
public interface PointcutParameter {
    String getName();

    Class getType();

    Object getBinding();
}