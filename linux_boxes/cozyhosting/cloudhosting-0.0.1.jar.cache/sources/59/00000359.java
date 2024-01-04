package com.fasterxml.jackson.core.io.doubleparser;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jackson-core-2.14.1.jar:com/fasterxml/jackson/core/io/doubleparser/FastDoubleSwar.class */
class FastDoubleSwar {
    FastDoubleSwar() {
    }

    public static int tryToParseEightDigitsUtf16(char[] a, int offset) {
        long first = a[offset] | (a[offset + 1] << 16) | (a[offset + 2] << 32) | (a[offset + 3] << 48);
        long second = a[offset + 4] | (a[offset + 5] << 16) | (a[offset + 6] << 32) | (a[offset + 7] << 48);
        return tryToParseEightDigitsUtf16(first, second);
    }

    public static int tryToParseEightDigitsUtf16(long first, long second) {
        long fval = first - 13511005043687472L;
        long sval = second - 13511005043687472L;
        long fpre = (first + 19703549022044230L) | fval;
        long spre = (second + 19703549022044230L) | sval;
        if (((fpre | spre) & (-35747867511423104L)) != 0) {
            return -1;
        }
        return ((int) ((sval * 281475406208040961L) >>> 48)) + (((int) ((fval * 281475406208040961L) >>> 48)) * 10000);
    }

    public static int tryToParseEightDigitsUtf8(byte[] a, int offset) {
        return tryToParseEightDigitsUtf8(readLongFromByteArrayLittleEndian(a, offset));
    }

    public static int tryToParseEightDigitsUtf8(long chunk) {
        long val = chunk - 3472328296227680304L;
        long predicate = ((chunk + 5063812098665367110L) | val) & (-9187201950435737472L);
        if (predicate != 0) {
            return -1;
        }
        long val2 = (val * 2561) >>> 8;
        return (int) ((((val2 & 1095216660735L) * 4294967296000100L) + (((val2 >>> 16) & 1095216660735L) * 42949672960001L)) >>> 32);
    }

    public static long tryToParseEightHexDigitsUtf16(char[] a, int offset) {
        long first = (a[offset] << 48) | (a[offset + 1] << 32) | (a[offset + 2] << 16) | a[offset + 3];
        long second = (a[offset + 4] << 48) | (a[offset + 5] << 32) | (a[offset + 6] << 16) | a[offset + 7];
        return tryToParseEightHexDigitsUtf16(first, second);
    }

    public static long tryToParseEightHexDigitsUtf16(long first, long second) {
        long lfirst = tryToParseFourHexDigitsUtf16(first);
        long lsecond = tryToParseFourHexDigitsUtf16(second);
        return (lfirst << 16) | lsecond;
    }

    public static long tryToParseEightHexDigitsUtf8(byte[] a, int offset) {
        return tryToParseEightHexDigitsUtf8(readLongFromByteArrayBigEndian(a, offset));
    }

    public static long tryToParseEightHexDigitsUtf8(long chunk) {
        long vec = chunk - 3472328296227680304L;
        long gt_09 = (vec + 8536140394893047414L) & (-9187201950435737472L);
        long ge_30 = vec + 5714873654208057167L;
        long ge_302 = ge_30 & (-9187201950435737472L);
        long le_37 = 3978709506094217015L + (vec ^ 9187201950435737471L);
        if (gt_09 != (ge_302 & le_37)) {
            return -1L;
        }
        long gt_09mask = (gt_09 >>> 7) * 255;
        long v = (vec & (gt_09mask ^ (-1))) | (vec - (2821266740684990247L & gt_09mask));
        long v2 = v | (v >>> 4);
        long v3 = v2 & 71777214294589695L;
        long v4 = v3 | (v3 >>> 8);
        long v5 = ((v4 >>> 16) & 4294901760L) | (v4 & 65535);
        return v5;
    }

    public static long tryToParseFourHexDigitsUtf16(long chunk) {
        long vec = chunk - 13511005043687472L;
        long gt_09 = (vec + 9220697983773212662L) & (-9223231297218904064L);
        long ge_30 = vec + 9209720292175216591L;
        long ge_302 = ge_30 & (-9223231297218904064L);
        long le_37 = 15481359945891895L + (vec ^ 9223231297218904063L);
        if (gt_09 != (ge_302 & le_37)) {
            return -1L;
        }
        long gt_09mask = (gt_09 >>> 15) * 65535;
        long v = (vec & (gt_09mask ^ (-1))) | (vec - (10977691597996071L & gt_09mask));
        long v2 = v | (v >>> 12);
        long v5 = (v2 | (v2 >>> 24)) & 65535;
        return v5;
    }

    public static long readLongFromByteArrayLittleEndian(byte[] a, int offset) {
        return ((a[offset + 7] & 255) << 56) | ((a[offset + 6] & 255) << 48) | ((a[offset + 5] & 255) << 40) | ((a[offset + 4] & 255) << 32) | ((a[offset + 3] & 255) << 24) | ((a[offset + 2] & 255) << 16) | ((a[offset + 1] & 255) << 8) | (a[offset] & 255);
    }

    public static long readLongFromByteArrayBigEndian(byte[] a, int offset) {
        return ((a[offset] & 255) << 56) | ((a[offset + 1] & 255) << 48) | ((a[offset + 2] & 255) << 40) | ((a[offset + 3] & 255) << 32) | ((a[offset + 4] & 255) << 24) | ((a[offset + 5] & 255) << 16) | ((a[offset + 6] & 255) << 8) | (a[offset + 7] & 255);
    }
}