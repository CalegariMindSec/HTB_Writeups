package io.micrometer.observation.transport;

import io.micrometer.common.lang.Nullable;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/micrometer-observation-1.10.3.jar:io/micrometer/observation/transport/ResponseContext.class */
public interface ResponseContext<RES> {
    @Nullable
    RES getResponse();

    void setResponse(RES res);
}