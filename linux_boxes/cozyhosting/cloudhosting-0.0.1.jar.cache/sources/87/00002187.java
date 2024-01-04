package org.apache.tomcat.util.security;

import java.security.PrivilegedAction;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-core-10.1.5.jar:org/apache/tomcat/util/security/PrivilegedGetTccl.class */
public class PrivilegedGetTccl implements PrivilegedAction<ClassLoader> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.security.PrivilegedAction
    public ClassLoader run() {
        return Thread.currentThread().getContextClassLoader();
    }
}