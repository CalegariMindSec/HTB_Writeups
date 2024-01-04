package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import java.security.AccessController;
import java.security.PrivilegedAction;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jaxb-runtime-4.0.1.jar:org/glassfish/jaxb/runtime/v2/runtime/reflect/opt/SecureLoader.class */
class SecureLoader {
    SecureLoader() {
    }

    static ClassLoader getContextClassLoader() {
        if (System.getSecurityManager() == null) {
            return Thread.currentThread().getContextClassLoader();
        }
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.glassfish.jaxb.runtime.v2.runtime.reflect.opt.SecureLoader.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                return Thread.currentThread().getContextClassLoader();
            }
        });
    }

    static ClassLoader getClassClassLoader(final Class c) {
        if (System.getSecurityManager() == null) {
            return c.getClassLoader();
        }
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.glassfish.jaxb.runtime.v2.runtime.reflect.opt.SecureLoader.2
            @Override // java.security.PrivilegedAction
            public Object run() {
                return c.getClassLoader();
            }
        });
    }

    static ClassLoader getSystemClassLoader() {
        if (System.getSecurityManager() == null) {
            return ClassLoader.getSystemClassLoader();
        }
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.glassfish.jaxb.runtime.v2.runtime.reflect.opt.SecureLoader.3
            @Override // java.security.PrivilegedAction
            public Object run() {
                return ClassLoader.getSystemClassLoader();
            }
        });
    }
}