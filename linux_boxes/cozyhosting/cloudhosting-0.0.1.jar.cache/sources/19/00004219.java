package org.jboss.logging;

import java.security.AccessController;
import java.security.PrivilegedAction;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jboss-logging-3.5.0.Final.jar:org/jboss/logging/SecurityActions.class */
class SecurityActions {
    SecurityActions() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getSystemProperty(final String key) {
        if (System.getSecurityManager() == null) {
            return System.getProperty(key);
        }
        return (String) AccessController.doPrivileged(new PrivilegedAction<String>() { // from class: org.jboss.logging.SecurityActions.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.security.PrivilegedAction
            public String run() {
                return System.getProperty(key);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getSystemProperty(final String key, final String dft) {
        if (System.getSecurityManager() == null) {
            return System.getProperty(key, dft);
        }
        return (String) AccessController.doPrivileged(new PrivilegedAction<String>() { // from class: org.jboss.logging.SecurityActions.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.security.PrivilegedAction
            public String run() {
                return System.getProperty(key, dft);
            }
        });
    }
}