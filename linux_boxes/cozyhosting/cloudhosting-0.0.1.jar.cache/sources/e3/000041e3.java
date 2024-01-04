package org.jboss.jandex;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jandex-2.4.2.Final.jar:org/jboss/jandex/PackedDataOutputStream.class */
class PackedDataOutputStream extends DataOutputStream {
    static final int MAX_1BYTE = 127;
    static final int MAX_2BYTE = 16383;
    static final int MAX_3BYTE = 2097151;
    static final int MAX_4BYTE = 268435455;

    public PackedDataOutputStream(OutputStream out) {
        super(out);
    }

    public void writePackedU32(int i) throws IOException {
        if ((i & (-128)) == 0) {
            writeByte(i);
        } else if ((i & (-16384)) == 0) {
            writeByte(((i >>> 7) & 127) | 128);
            writeByte(i & 127);
        } else if ((i & (-2097152)) == 0) {
            writeByte(((i >>> 14) & 127) | 128);
            writeByte(((i >>> 7) & 127) | 128);
            writeByte(i & 127);
        } else if ((i & (-268435456)) == 0) {
            writeByte(((i >>> 21) & 127) | 128);
            writeByte(((i >>> 14) & 127) | 128);
            writeByte(((i >>> 7) & 127) | 128);
            writeByte(i & 127);
        } else {
            writeByte(((i >>> 28) & 127) | 128);
            writeByte(((i >>> 21) & 127) | 128);
            writeByte(((i >>> 14) & 127) | 128);
            writeByte(((i >>> 7) & 127) | 128);
            writeByte(i & 127);
        }
    }
}