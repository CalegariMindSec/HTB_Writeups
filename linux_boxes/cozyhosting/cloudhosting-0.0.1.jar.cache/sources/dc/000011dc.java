package net.bytebuddy.build;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.build.CachedReturnPlugin;

@SuppressFBWarnings(value = {"NM_CLASS_NAMING_CONVENTION"}, justification = "Name is chosen to optimize for simple lookup")
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/build/CachedReturnPlugin$Advice$boolean.class */
class CachedReturnPlugin$Advice$boolean {
    private CachedReturnPlugin$Advice$boolean() {
        throw new UnsupportedOperationException("This class is merely an advice template and should not be instantiated");
    }

    @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
    protected static boolean enter(@CachedReturnPlugin.CacheField boolean cached) {
        return cached;
    }

    @Advice.OnMethodExit
    @SuppressFBWarnings(value = {"UC_USELESS_VOID_METHOD", "IP_PARAMETER_IS_DEAD_BUT_OVERWRITTEN"}, justification = "Advice method serves as a template")
    protected static void exit(@Advice.Return(readOnly = false) boolean returned, @CachedReturnPlugin.CacheField boolean cached) {
        if (returned) {
        }
    }
}