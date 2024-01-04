package org.aspectj.apache.bcel.util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/apache/bcel/util/ByteSequence.class */
public final class ByteSequence extends DataInputStream {
    private ByteArrayStream byte_stream;

    public ByteSequence(byte[] bytes) {
        super(new ByteArrayStream(bytes));
        this.byte_stream = (ByteArrayStream) this.in;
    }

    public final int getIndex() {
        return this.byte_stream.getPosition();
    }

    final void unreadByte() {
        this.byte_stream.unreadByte();
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/apache/bcel/util/ByteSequence$ByteArrayStream.class */
    private static final class ByteArrayStream extends ByteArrayInputStream {
        ByteArrayStream(byte[] bytes) {
            super(bytes);
        }

        final int getPosition() {
            return this.pos;
        }

        final void unreadByte() {
            if (this.pos > 0) {
                this.pos--;
            }
        }
    }
}