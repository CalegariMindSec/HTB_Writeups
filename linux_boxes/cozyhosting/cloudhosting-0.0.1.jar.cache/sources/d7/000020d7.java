package org.apache.tomcat.util.json;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-core-10.1.5.jar:org/apache/tomcat/util/json/JSONFilter.class */
public class JSONFilter {
    private JSONFilter() {
    }

    public static String escape(String input) {
        char[] chars = input.toCharArray();
        StringBuffer escaped = null;
        int lastUnescapedStart = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] < ' ' || chars[i] == '\"' || chars[i] == '\\') {
                if (escaped == null) {
                    escaped = new StringBuffer(chars.length + 20);
                }
                if (lastUnescapedStart < i) {
                    escaped.append(input.subSequence(lastUnescapedStart, i));
                }
                lastUnescapedStart = i + 1;
                escaped.append("\\u");
                escaped.append(String.format("%04X", Integer.valueOf(chars[i])));
            }
        }
        if (escaped == null) {
            return input;
        }
        if (lastUnescapedStart < chars.length) {
            escaped.append(input.subSequence(lastUnescapedStart, chars.length));
        }
        return escaped.toString();
    }
}