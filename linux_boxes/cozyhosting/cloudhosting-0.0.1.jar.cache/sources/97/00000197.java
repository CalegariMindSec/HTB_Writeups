package ch.qos.logback.core.net;

import java.io.IOException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/logback-core-1.4.5.jar:ch/qos/logback/core/net/ObjectWriter.class */
public interface ObjectWriter {
    void write(Object obj) throws IOException;
}