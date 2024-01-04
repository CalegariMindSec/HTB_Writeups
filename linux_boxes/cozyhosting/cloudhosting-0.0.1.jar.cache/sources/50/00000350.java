package com.fasterxml.jackson.core.io.doubleparser;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jackson-core-2.14.1.jar:com/fasterxml/jackson/core/io/doubleparser/AbstractFloatValueParser.class */
abstract class AbstractFloatValueParser {
    static final long PARSE_ERROR = -1;
    static final long MINIMAL_NINETEEN_DIGIT_INTEGER = 1000000000000000000L;
    static final int MAX_EXPONENT_NUMBER = 1024;
    static final byte DECIMAL_POINT_CLASS = -4;
    static final byte OTHER_CLASS = -1;
    static final byte[] CHAR_TO_HEX_MAP = new byte[128];

    static {
        char c = 0;
        while (true) {
            char ch2 = c;
            if (ch2 >= CHAR_TO_HEX_MAP.length) {
                break;
            }
            CHAR_TO_HEX_MAP[ch2] = -1;
            c = (char) (ch2 + 1);
        }
        char c2 = '0';
        while (true) {
            char ch3 = c2;
            if (ch3 > '9') {
                break;
            }
            CHAR_TO_HEX_MAP[ch3] = (byte) (ch3 - '0');
            c2 = (char) (ch3 + 1);
        }
        char c3 = 'A';
        while (true) {
            char ch4 = c3;
            if (ch4 > 'F') {
                break;
            }
            CHAR_TO_HEX_MAP[ch4] = (byte) ((ch4 - 'A') + 10);
            c3 = (char) (ch4 + 1);
        }
        char c4 = 'a';
        while (true) {
            char ch5 = c4;
            if (ch5 > 'f') {
                break;
            }
            CHAR_TO_HEX_MAP[ch5] = (byte) ((ch5 - 'a') + 10);
            c4 = (char) (ch5 + 1);
        }
        char c5 = '.';
        while (true) {
            char ch6 = c5;
            if (ch6 <= '.') {
                CHAR_TO_HEX_MAP[ch6] = -4;
                c5 = (char) (ch6 + 1);
            } else {
                return;
            }
        }
    }
}