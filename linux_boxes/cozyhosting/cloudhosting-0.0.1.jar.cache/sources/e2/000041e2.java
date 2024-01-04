package org.jboss.jandex;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jandex-2.4.2.Final.jar:org/jboss/jandex/PackedDataInputStream.class */
class PackedDataInputStream extends DataInputStream {
    static final int MAX_1BYTE = 127;

    public PackedDataInputStream(InputStream in) {
        super(in);
    }

    public int readPackedU32() throws IOException {
        byte b;
        int i = 0;
        do {
            b = readByte();
            i = (i << 7) | (b & Byte.MAX_VALUE);
        } while ((b & 128) == 128);
        return i;
    }
}