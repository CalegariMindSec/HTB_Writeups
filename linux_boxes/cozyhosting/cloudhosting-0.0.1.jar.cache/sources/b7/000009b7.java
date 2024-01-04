package io.micrometer.observation.transport;

import io.micrometer.common.lang.Nullable;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/micrometer-observation-1.10.3.jar:io/micrometer/observation/transport/Propagator.class */
public interface Propagator {

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/micrometer-observation-1.10.3.jar:io/micrometer/observation/transport/Propagator$Getter.class */
    public interface Getter<C> {
        @Nullable
        String get(C c, String str);
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/micrometer-observation-1.10.3.jar:io/micrometer/observation/transport/Propagator$Setter.class */
    public interface Setter<C> {
        void set(@Nullable C c, String str, String str2);
    }
}