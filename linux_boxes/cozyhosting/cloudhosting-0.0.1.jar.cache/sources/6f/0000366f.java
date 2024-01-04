package org.hibernate.internal.util;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/ExceptionHelper.class */
public final class ExceptionHelper {
    private ExceptionHelper() {
    }

    public static void doThrow(Throwable e) {
        doThrow0(e);
    }

    private static <T extends Throwable> void doThrow0(Throwable e) throws Throwable {
        throw e;
    }
}