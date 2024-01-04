package com.sun.istack.logging;

import java.lang.StackWalker;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/istack-commons-runtime-4.1.1.jar:com/sun/istack/logging/StackHelper.class */
class StackHelper {
    StackHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getCallerMethodName() {
        return (String) StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).walk(frames -> {
            return (String) frames.dropWhile(f -> {
                return !Logger.class.equals(f.getDeclaringClass());
            }).dropWhile(f2 -> {
                return Logger.class.equals(f2.getDeclaringClass());
            }).findFirst().map((v0) -> {
                return v0.getMethodName();
            }).orElse("UNKNOWN METHOD");
        });
    }
}