package org.jboss.jandex;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jandex-2.4.2.Final.jar:org/jboss/jandex/Modifiers.class */
final class Modifiers {
    static final int ENUM = 16384;
    static final int SYNTHETIC = 4096;
    static final int ANNOTATION = 8192;

    Modifiers() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSynthetic(int mod) {
        return (mod & 4096) != 0;
    }
}