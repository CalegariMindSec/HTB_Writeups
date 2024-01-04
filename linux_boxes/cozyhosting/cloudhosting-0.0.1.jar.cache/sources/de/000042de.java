package org.postgresql.core;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/core/VisibleBufferedInputStream.class */
public class VisibleBufferedInputStream extends InputStream {
    private static final int MINIMUM_READ = 1024;
    private static final int STRING_SCAN_SPAN = 1024;
    private final InputStream wrapped;
    private byte[] buffer;
    private int index;
    private int endIndex;
    private boolean timeoutRequested = false;

    public VisibleBufferedInputStream(InputStream in, int bufferSize) {
        this.wrapped = in;
        this.buffer = new byte[bufferSize < 1024 ? 1024 : bufferSize];
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (ensureBytes(1)) {
            byte[] bArr = this.buffer;
            int i = this.index;
            this.index = i + 1;
            return bArr[i] & 255;
        }
        return -1;
    }

    public int peek() throws IOException {
        if (ensureBytes(1)) {
            return this.buffer[this.index] & 255;
        }
        return -1;
    }

    public byte readRaw() {
        byte[] bArr = this.buffer;
        int i = this.index;
        this.index = i + 1;
        return bArr[i];
    }

    public boolean ensureBytes(int n) throws IOException {
        return ensureBytes(n, true);
    }

    public boolean ensureBytes(int n, boolean block) throws IOException {
        int i = n - this.endIndex;
        int i2 = this.index;
        while (true) {
            int required = i + i2;
            if (required > 0) {
                if (!readMore(required, block)) {
                    return false;
                }
                i = n - this.endIndex;
                i2 = this.index;
            } else {
                return true;
            }
        }
    }

    private boolean readMore(int wanted, boolean block) throws IOException {
        if (this.endIndex == this.index) {
            this.index = 0;
            this.endIndex = 0;
        }
        int canFit = this.buffer.length - this.endIndex;
        if (canFit < wanted) {
            if (this.index + canFit > wanted + 1024) {
                compact();
            } else {
                doubleBuffer();
            }
            canFit = this.buffer.length - this.endIndex;
        }
        int read = 0;
        try {
            read = this.wrapped.read(this.buffer, this.endIndex, canFit);
            if (!block && read == 0) {
                return false;
            }
        } catch (SocketTimeoutException e) {
            if (!block) {
                return false;
            }
            if (this.timeoutRequested) {
                throw e;
            }
        }
        if (read < 0) {
            return false;
        }
        this.endIndex += read;
        return true;
    }

    private void doubleBuffer() {
        byte[] buf = new byte[this.buffer.length * 2];
        moveBufferTo(buf);
        this.buffer = buf;
    }

    private void compact() {
        moveBufferTo(this.buffer);
    }

    private void moveBufferTo(byte[] dest) {
        int size = this.endIndex - this.index;
        System.arraycopy(this.buffer, this.index, dest, 0, size);
        this.index = 0;
        this.endIndex = size;
    }

    @Override // java.io.InputStream
    public int read(byte[] to, int off, int len) throws IOException {
        if ((off | len | (off + len) | (to.length - (off + len))) < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (len == 0) {
            return 0;
        }
        int avail = this.endIndex - this.index;
        if (len - avail < 1024) {
            ensureBytes(len);
            avail = this.endIndex - this.index;
        }
        if (avail > 0) {
            if (len <= avail) {
                System.arraycopy(this.buffer, this.index, to, off, len);
                this.index += len;
                return len;
            }
            System.arraycopy(this.buffer, this.index, to, off, avail);
            len -= avail;
            off += avail;
        }
        int read = avail;
        this.index = 0;
        this.endIndex = 0;
        do {
            try {
                int r = this.wrapped.read(to, off, len);
                if (r <= 0) {
                    return read == 0 ? r : read;
                }
                read += r;
                off += r;
                len -= r;
            } catch (SocketTimeoutException e) {
                if (read == 0 && this.timeoutRequested) {
                    throw e;
                }
                return read;
            }
        } while (len > 0);
        return read;
    }

    @Override // java.io.InputStream
    public long skip(long n) throws IOException {
        int avail = this.endIndex - this.index;
        if (avail >= n) {
            this.index = (int) (this.index + n);
            return n;
        }
        long n2 = n - avail;
        this.index = 0;
        this.endIndex = 0;
        return avail + this.wrapped.skip(n2);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        int avail = this.endIndex - this.index;
        return avail > 0 ? avail : this.wrapped.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.wrapped.close();
    }

    public byte[] getBuffer() {
        return this.buffer;
    }

    public int getIndex() {
        return this.index;
    }

    public int scanCStringLength() throws IOException {
        int pos = this.index;
        while (true) {
            if (pos < this.endIndex) {
                int i = pos;
                pos++;
                if (this.buffer[i] == 0) {
                    return pos - this.index;
                }
            } else if (!readMore(1024, true)) {
                throw new EOFException();
            } else {
                pos = this.index;
            }
        }
    }

    public void setTimeoutRequested(boolean timeoutRequested) {
        this.timeoutRequested = timeoutRequested;
    }

    public InputStream getWrapped() {
        return this.wrapped;
    }
}