package org.postgresql.gss;

import java.io.IOException;
import java.io.OutputStream;
import org.ietf.jgss.GSSContext;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.MessageProp;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/gss/GSSOutputStream.class */
public class GSSOutputStream extends OutputStream {
    private final GSSContext gssContext;
    private final MessageProp messageProp;
    private byte[] buffer;
    private byte[] int4Buf = new byte[4];
    private int index;
    private OutputStream wrapped;

    public GSSOutputStream(OutputStream out, GSSContext gssContext, MessageProp messageProp, int bufferSize) {
        this.wrapped = out;
        this.gssContext = gssContext;
        this.messageProp = messageProp;
        this.buffer = new byte[bufferSize];
    }

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        byte[] bArr = this.buffer;
        int i = this.index;
        this.index = i + 1;
        bArr[i] = (byte) b;
        if (this.index >= this.buffer.length) {
            flush();
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] buf) throws IOException {
        write(buf, 0, buf.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] b, int pos, int len) throws IOException {
        while (len > 0) {
            int roomToWrite = this.buffer.length - this.index;
            if (len < roomToWrite) {
                System.arraycopy(b, pos, this.buffer, this.index, len);
                this.index += len;
                len -= roomToWrite;
            } else {
                System.arraycopy(b, pos, this.buffer, this.index, roomToWrite);
                this.index += roomToWrite;
                len -= roomToWrite;
            }
            if (roomToWrite == 0) {
                flush();
            }
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        try {
            byte[] token = this.gssContext.wrap(this.buffer, 0, this.index, this.messageProp);
            sendInteger4Raw(token.length);
            this.wrapped.write(token, 0, token.length);
            this.index = 0;
            this.wrapped.flush();
        } catch (GSSException ex) {
            throw new IOException((Throwable) ex);
        }
    }

    private void sendInteger4Raw(int val) throws IOException {
        this.int4Buf[0] = (byte) (val >>> 24);
        this.int4Buf[1] = (byte) (val >>> 16);
        this.int4Buf[2] = (byte) (val >>> 8);
        this.int4Buf[3] = (byte) val;
        this.wrapped.write(this.int4Buf);
    }
}