package org.aopalliance.intercept;

import java.lang.reflect.AccessibleObject;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/spring-aop-6.0.4.jar:org/aopalliance/intercept/Joinpoint.class */
public interface Joinpoint {
    @Nullable
    Object proceed() throws Throwable;

    @Nullable
    Object getThis();

    @Nonnull
    AccessibleObject getStaticPart();
}