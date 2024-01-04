package io.micrometer.core.instrument.util;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/micrometer-core-1.10.3.jar:io/micrometer/core/instrument/util/StringEscapeUtils.class */
public final class StringEscapeUtils {
    private static final String[] REPLACEMENT_CHARS = new String[128];
    private static final String U2028 = "\\u2028";
    private static final String U2029 = "\\u2029";

    /* JADX WARN: Removed duplicated region for block: B:26:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x007a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String escapeJson(@io.micrometer.common.lang.Nullable java.lang.String r5) {
        /*
            r0 = r5
            if (r0 != 0) goto L7
            java.lang.String r0 = ""
            return r0
        L7:
            r0 = r5
            int r0 = r0.length()
            r6 = r0
            r0 = r6
            if (r0 != 0) goto L12
            r0 = r5
            return r0
        L12:
            r0 = 0
            r7 = r0
            r0 = 0
            r8 = r0
            r0 = 0
            r9 = r0
        L19:
            r0 = r9
            r1 = r6
            if (r0 >= r1) goto L95
            r0 = r5
            r1 = r9
            char r0 = r0.charAt(r1)
            r10 = r0
            r0 = r10
            r1 = 128(0x80, float:1.794E-43)
            if (r0 >= r1) goto L3f
            java.lang.String[] r0 = io.micrometer.core.instrument.util.StringEscapeUtils.REPLACEMENT_CHARS
            r1 = r10
            r0 = r0[r1]
            r11 = r0
            r0 = r11
            if (r0 != 0) goto L5a
            goto L8f
        L3f:
            r0 = r10
            r1 = 8232(0x2028, float:1.1535E-41)
            if (r0 != r1) goto L4e
            java.lang.String r0 = "\\u2028"
            r11 = r0
            goto L5a
        L4e:
            r0 = r10
            r1 = 8233(0x2029, float:1.1537E-41)
            if (r0 != r1) goto L8f
            java.lang.String r0 = "\\u2029"
            r11 = r0
        L5a:
            r0 = r7
            r1 = r9
            if (r0 >= r1) goto L76
            r0 = r8
            if (r0 != 0) goto L6d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = r0
            r2 = r6
            r1.<init>(r2)
            r8 = r0
        L6d:
            r0 = r8
            r1 = r5
            r2 = r7
            r3 = r9
            java.lang.StringBuilder r0 = r0.append(r1, r2, r3)
        L76:
            r0 = r8
            if (r0 != 0) goto L83
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = r0
            r2 = r6
            r1.<init>(r2)
            r8 = r0
        L83:
            r0 = r8
            r1 = r11
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r9
            r1 = 1
            int r0 = r0 + r1
            r7 = r0
        L8f:
            int r9 = r9 + 1
            goto L19
        L95:
            r0 = r8
            if (r0 != 0) goto L9b
            r0 = r5
            return r0
        L9b:
            r0 = r7
            r1 = r6
            if (r0 >= r1) goto La8
            r0 = r8
            r1 = r5
            r2 = r7
            r3 = r6
            java.lang.StringBuilder r0 = r0.append(r1, r2, r3)
        La8:
            r0 = r8
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.micrometer.core.instrument.util.StringEscapeUtils.escapeJson(java.lang.String):java.lang.String");
    }

    static {
        for (int i = 0; i <= 31; i++) {
            REPLACEMENT_CHARS[i] = String.format("\\u%04x", Integer.valueOf(i));
        }
        REPLACEMENT_CHARS[34] = "\\\"";
        REPLACEMENT_CHARS[92] = "\\\\";
        REPLACEMENT_CHARS[9] = "\\t";
        REPLACEMENT_CHARS[8] = "\\b";
        REPLACEMENT_CHARS[10] = "\\n";
        REPLACEMENT_CHARS[13] = "\\r";
        REPLACEMENT_CHARS[12] = "\\f";
    }

    private StringEscapeUtils() {
    }
}