package jakarta.xml.bind;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.xml.bind-api-4.0.0.jar:jakarta/xml/bind/WhiteSpaceProcessor.class */
abstract class WhiteSpaceProcessor {
    WhiteSpaceProcessor() {
    }

    public static String replace(String text) {
        return replace((CharSequence) text).toString();
    }

    public static CharSequence replace(CharSequence text) {
        int i = text.length() - 1;
        while (i >= 0 && !isWhiteSpaceExceptSpace(text.charAt(i))) {
            i--;
        }
        if (i < 0) {
            return text;
        }
        StringBuilder buf = new StringBuilder(text);
        int i2 = i;
        buf.setCharAt(i2, ' ');
        for (int i3 = i - 1; i3 >= 0; i3--) {
            if (isWhiteSpaceExceptSpace(buf.charAt(i3))) {
                buf.setCharAt(i3, ' ');
            }
        }
        return new String(buf);
    }

    public static CharSequence trim(CharSequence text) {
        int len = text.length();
        int start = 0;
        while (start < len && isWhiteSpace(text.charAt(start))) {
            start++;
        }
        int end = len - 1;
        while (end > start && isWhiteSpace(text.charAt(end))) {
            end--;
        }
        if (start == 0 && end == len - 1) {
            return text;
        }
        return text.subSequence(start, end + 1);
    }

    public static String collapse(String text) {
        return collapse((CharSequence) text).toString();
    }

    public static CharSequence collapse(CharSequence text) {
        int len = text.length();
        int s = 0;
        while (s < len && !isWhiteSpace(text.charAt(s))) {
            s++;
        }
        if (s == len) {
            return text;
        }
        StringBuilder result = new StringBuilder(len);
        if (s != 0) {
            for (int i = 0; i < s; i++) {
                result.append(text.charAt(i));
            }
            result.append(' ');
        }
        boolean inStripMode = true;
        for (int i2 = s + 1; i2 < len; i2++) {
            char ch2 = text.charAt(i2);
            boolean b = isWhiteSpace(ch2);
            if (!inStripMode || !b) {
                inStripMode = b;
                if (inStripMode) {
                    result.append(' ');
                } else {
                    result.append(ch2);
                }
            }
        }
        int len2 = result.length();
        if (len2 > 0 && result.charAt(len2 - 1) == ' ') {
            result.setLength(len2 - 1);
        }
        return result;
    }

    public static final boolean isWhiteSpace(CharSequence s) {
        for (int i = s.length() - 1; i >= 0; i--) {
            if (!isWhiteSpace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static final boolean isWhiteSpace(char ch2) {
        if (ch2 > ' ') {
            return false;
        }
        return ch2 == '\t' || ch2 == '\n' || ch2 == '\r' || ch2 == ' ';
    }

    protected static final boolean isWhiteSpaceExceptSpace(char ch2) {
        if (ch2 >= ' ') {
            return false;
        }
        return ch2 == '\t' || ch2 == '\n' || ch2 == '\r';
    }
}