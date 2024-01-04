package org.apache.coyote;

import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-core-10.1.5.jar:org/apache/coyote/OutputBuffer.class */
public interface OutputBuffer {
    int doWrite(ByteBuffer byteBuffer) throws IOException;

    long getBytesWritten();
}