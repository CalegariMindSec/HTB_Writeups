package org.glassfish.jaxb.runtime.api;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jaxb-runtime-4.0.1.jar:org/glassfish/jaxb/runtime/api/ClassResolver.class */
public abstract class ClassResolver {
    @Nullable
    public abstract Class<?> resolveElementName(@NotNull String str, @NotNull String str2) throws Exception;

    protected ClassResolver() {
    }
}