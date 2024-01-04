package org.aspectj.lang;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/lang/Signature.class */
public interface Signature {
    String toString();

    String toShortString();

    String toLongString();

    String getName();

    int getModifiers();

    Class getDeclaringType();

    String getDeclaringTypeName();
}