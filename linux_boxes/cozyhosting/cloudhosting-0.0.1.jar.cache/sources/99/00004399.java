package org.postgresql.replication;

import java.nio.ByteBuffer;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/replication/LogSequenceNumber.class */
public final class LogSequenceNumber implements Comparable<LogSequenceNumber> {
    public static final LogSequenceNumber INVALID_LSN = valueOf(0);
    private final long value;

    private LogSequenceNumber(long value) {
        this.value = value;
    }

    public static LogSequenceNumber valueOf(long value) {
        return new LogSequenceNumber(value);
    }

    public static LogSequenceNumber valueOf(String strValue) {
        int slashIndex = strValue.lastIndexOf(47);
        if (slashIndex <= 0) {
            return INVALID_LSN;
        }
        String logicalXLogStr = strValue.substring(0, slashIndex);
        int logicalXlog = (int) Long.parseLong(logicalXLogStr, 16);
        String segmentStr = strValue.substring(slashIndex + 1, strValue.length());
        int segment = (int) Long.parseLong(segmentStr, 16);
        ByteBuffer buf = ByteBuffer.allocate(8);
        buf.putInt(logicalXlog);
        buf.putInt(segment);
        buf.position(0);
        long value = buf.getLong();
        return valueOf(value);
    }

    public long asLong() {
        return this.value;
    }

    public String asString() {
        ByteBuffer buf = ByteBuffer.allocate(8);
        buf.putLong(this.value);
        buf.position(0);
        int logicalXlog = buf.getInt();
        int segment = buf.getInt();
        return String.format("%X/%X", Integer.valueOf(logicalXlog), Integer.valueOf(segment));
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LogSequenceNumber that = (LogSequenceNumber) o;
        return this.value == that.value;
    }

    public int hashCode() {
        return (int) (this.value ^ (this.value >>> 32));
    }

    public String toString() {
        return "LSN{" + asString() + '}';
    }

    @Override // java.lang.Comparable
    public int compareTo(LogSequenceNumber o) {
        if (this.value == o.value) {
            return 0;
        }
        return this.value + Long.MIN_VALUE < o.value + Long.MIN_VALUE ? -1 : 1;
    }
}