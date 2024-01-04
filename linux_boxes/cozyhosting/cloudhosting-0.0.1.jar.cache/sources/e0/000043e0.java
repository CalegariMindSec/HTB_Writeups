package org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/shaded/com/ongres/scram/common/bouncycastle/pbkdf2/Strings.class */
public final class Strings {
    public static byte[] toUTF8ByteArray(char[] string) {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        try {
            toUTF8ByteArray(string, bOut);
            return bOut.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException("cannot encode string to byte array!");
        }
    }

    public static void toUTF8ByteArray(char[] string, OutputStream sOut) throws IOException {
        int i = 0;
        while (i < string.length) {
            char ch2 = string[i];
            if (ch2 < 128) {
                sOut.write(ch2);
            } else if (ch2 < 2048) {
                sOut.write(192 | (ch2 >> 6));
                sOut.write(128 | (ch2 & '?'));
            } else if (ch2 >= 55296 && ch2 <= 57343) {
                if (i + 1 >= string.length) {
                    throw new IllegalStateException("invalid UTF-16 codepoint");
                }
                i++;
                char ch3 = string[i];
                if (ch2 <= 56319) {
                    int codePoint = (((ch2 & 1023) << 10) | (ch3 & 1023)) + 65536;
                    sOut.write(240 | (codePoint >> 18));
                    sOut.write(128 | ((codePoint >> 12) & 63));
                    sOut.write(128 | ((codePoint >> 6) & 63));
                    sOut.write(128 | (codePoint & 63));
                } else {
                    throw new IllegalStateException("invalid UTF-16 codepoint");
                }
            } else {
                sOut.write(224 | (ch2 >> '\f'));
                sOut.write(128 | ((ch2 >> 6) & 63));
                sOut.write(128 | (ch2 & '?'));
            }
            i++;
        }
    }

    public static String fromByteArray(byte[] bytes) {
        return new String(asCharArray(bytes));
    }

    public static char[] asCharArray(byte[] bytes) {
        char[] chars = new char[bytes.length];
        for (int i = 0; i != chars.length; i++) {
            chars[i] = (char) (bytes[i] & 255);
        }
        return chars;
    }
}