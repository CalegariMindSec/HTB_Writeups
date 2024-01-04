package org.HdrHistogram;

import java.nio.ByteBuffer;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/HdrHistogram-2.1.12.jar:org/HdrHistogram/ZigZagEncoding.class */
class ZigZagEncoding {
    ZigZagEncoding() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void putLong(ByteBuffer buffer, long value) {
        long value2 = (value << 1) ^ (value >> 63);
        if ((value2 >>> 7) == 0) {
            buffer.put((byte) value2);
            return;
        }
        buffer.put((byte) ((value2 & 127) | 128));
        if ((value2 >>> 14) == 0) {
            buffer.put((byte) (value2 >>> 7));
            return;
        }
        buffer.put((byte) ((value2 >>> 7) | 128));
        if ((value2 >>> 21) == 0) {
            buffer.put((byte) (value2 >>> 14));
            return;
        }
        buffer.put((byte) ((value2 >>> 14) | 128));
        if ((value2 >>> 28) == 0) {
            buffer.put((byte) (value2 >>> 21));
            return;
        }
        buffer.put((byte) ((value2 >>> 21) | 128));
        if ((value2 >>> 35) == 0) {
            buffer.put((byte) (value2 >>> 28));
            return;
        }
        buffer.put((byte) ((value2 >>> 28) | 128));
        if ((value2 >>> 42) == 0) {
            buffer.put((byte) (value2 >>> 35));
            return;
        }
        buffer.put((byte) ((value2 >>> 35) | 128));
        if ((value2 >>> 49) == 0) {
            buffer.put((byte) (value2 >>> 42));
            return;
        }
        buffer.put((byte) ((value2 >>> 42) | 128));
        if ((value2 >>> 56) == 0) {
            buffer.put((byte) (value2 >>> 49));
            return;
        }
        buffer.put((byte) ((value2 >>> 49) | 128));
        buffer.put((byte) (value2 >>> 56));
    }

    static void putInt(ByteBuffer buffer, int value) {
        int value2 = (value << 1) ^ (value >> 31);
        if ((value2 >>> 7) == 0) {
            buffer.put((byte) value2);
            return;
        }
        buffer.put((byte) ((value2 & 127) | 128));
        if ((value2 >>> 14) == 0) {
            buffer.put((byte) (value2 >>> 7));
            return;
        }
        buffer.put((byte) ((value2 >>> 7) | 128));
        if ((value2 >>> 21) == 0) {
            buffer.put((byte) (value2 >>> 14));
            return;
        }
        buffer.put((byte) ((value2 >>> 14) | 128));
        if ((value2 >>> 28) == 0) {
            buffer.put((byte) (value2 >>> 21));
            return;
        }
        buffer.put((byte) ((value2 >>> 21) | 128));
        buffer.put((byte) (value2 >>> 28));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long getLong(ByteBuffer buffer) {
        long v = buffer.get();
        long value = v & 127;
        if ((v & 128) != 0) {
            long v2 = buffer.get();
            value |= (v2 & 127) << 7;
            if ((v2 & 128) != 0) {
                long v3 = buffer.get();
                value |= (v3 & 127) << 14;
                if ((v3 & 128) != 0) {
                    long v4 = buffer.get();
                    value |= (v4 & 127) << 21;
                    if ((v4 & 128) != 0) {
                        long v5 = buffer.get();
                        value |= (v5 & 127) << 28;
                        if ((v5 & 128) != 0) {
                            long v6 = buffer.get();
                            value |= (v6 & 127) << 35;
                            if ((v6 & 128) != 0) {
                                long v7 = buffer.get();
                                value |= (v7 & 127) << 42;
                                if ((v7 & 128) != 0) {
                                    long v8 = buffer.get();
                                    value |= (v8 & 127) << 49;
                                    if ((v8 & 128) != 0) {
                                        value |= buffer.get() << 56;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return (value >>> 1) ^ (-(value & 1));
    }

    static int getInt(ByteBuffer buffer) {
        int v = buffer.get();
        int value = v & 127;
        if ((v & 128) != 0) {
            int v2 = buffer.get();
            value |= (v2 & 127) << 7;
            if ((v2 & 128) != 0) {
                int v3 = buffer.get();
                value |= (v3 & 127) << 14;
                if ((v3 & 128) != 0) {
                    int v4 = buffer.get();
                    value |= (v4 & 127) << 21;
                    if ((v4 & 128) != 0) {
                        value |= (buffer.get() & 127) << 28;
                    }
                }
            }
        }
        return (value >>> 1) ^ (-(value & 1));
    }
}