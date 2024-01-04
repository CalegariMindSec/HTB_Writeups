package org.postgresql.core;

import org.checkerframework.dataflow.qual.Pure;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/core/Tuple.class */
public class Tuple {
    private final boolean forUpdate;
    final byte[][] data;

    /* JADX WARN: Type inference failed for: r1v1, types: [byte[], byte[][]] */
    public Tuple(int length) {
        this(new byte[length], true);
    }

    public Tuple(byte[][] data) {
        this(data, false);
    }

    private Tuple(byte[][] data, boolean forUpdate) {
        this.data = data;
        this.forUpdate = forUpdate;
    }

    public int fieldCount() {
        return this.data.length;
    }

    public int length() {
        byte[][] bArr;
        int length = 0;
        for (byte[] field : this.data) {
            if (field != null) {
                length += field.length;
            }
        }
        return length;
    }

    @Pure
    public byte[] get(int index) {
        return this.data[index];
    }

    public Tuple updateableCopy() {
        return copy(true);
    }

    public Tuple readOnlyCopy() {
        return copy(false);
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [byte[], java.lang.Object, byte[][]] */
    private Tuple copy(boolean forUpdate) {
        ?? r0 = new byte[this.data.length];
        System.arraycopy(this.data, 0, r0, 0, this.data.length);
        return new Tuple(r0, forUpdate);
    }

    public void set(int index, byte[] fieldData) {
        if (!this.forUpdate) {
            throw new IllegalArgumentException("Attempted to write to readonly tuple");
        }
        this.data[index] = fieldData;
    }
}