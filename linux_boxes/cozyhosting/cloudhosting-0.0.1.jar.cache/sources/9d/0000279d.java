package org.glassfish.jaxb.core;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jaxb-core-4.0.1.jar:org/glassfish/jaxb/core/StackHelper.class */
final class StackHelper {
    private StackHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getCallerClassName() {
        return (String) StackWalker.getInstance().walk(frames -> {
            return (String) frames.map((v0) -> {
                return v0.getClassName();
            }).skip(2L).findFirst().get();
        });
    }
}