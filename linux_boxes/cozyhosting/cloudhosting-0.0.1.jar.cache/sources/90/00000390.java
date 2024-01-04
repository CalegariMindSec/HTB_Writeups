package com.fasterxml.jackson.core.util;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jackson-core-2.14.1.jar:com/fasterxml/jackson/core/util/JacksonFeature.class */
public interface JacksonFeature {
    boolean enabledByDefault();

    int getMask();

    boolean enabledIn(int i);
}