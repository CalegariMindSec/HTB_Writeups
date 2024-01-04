package org.aspectj.weaver.loadtime;

import java.security.ProtectionDomain;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/weaver/loadtime/ClassPreProcessor.class */
public interface ClassPreProcessor {
    void initialize();

    byte[] preProcess(String str, byte[] bArr, ClassLoader classLoader, ProtectionDomain protectionDomain);

    void prepareForRedefinition(ClassLoader classLoader, String str);
}