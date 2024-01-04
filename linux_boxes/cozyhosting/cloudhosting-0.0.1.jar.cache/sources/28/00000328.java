package com.fasterxml.jackson.core.async;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jackson-core-2.14.1.jar:com/fasterxml/jackson/core/async/NonBlockingInputFeeder.class */
public interface NonBlockingInputFeeder {
    boolean needMoreInput();

    void endOfInput();
}