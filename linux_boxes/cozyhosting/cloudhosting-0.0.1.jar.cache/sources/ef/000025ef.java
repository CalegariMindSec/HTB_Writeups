package org.attoparser;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/attoparser-2.0.6.RELEASE.jar:org/attoparser/ParsingLocatorUtil.class */
final class ParsingLocatorUtil {
    public static void countChar(int[] locator, char c) {
        if (c == '\n') {
            locator[0] = locator[0] + 1;
            locator[1] = 1;
            return;
        }
        locator[1] = locator[1] + 1;
    }

    private ParsingLocatorUtil() {
    }
}