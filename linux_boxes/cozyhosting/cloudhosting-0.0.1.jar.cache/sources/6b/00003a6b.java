package org.hibernate.query.internal;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/query/internal/QueryLiteralHelper.class */
public class QueryLiteralHelper {
    private QueryLiteralHelper() {
    }

    public static String toStringLiteral(String value) {
        StringBuilder sb = new StringBuilder(value.length() + 2);
        appendStringLiteral(sb, value);
        return sb.toString();
    }

    public static void appendStringLiteral(StringBuilder sb, String value) {
        sb.append('\'');
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c == '\'') {
                sb.append('\'');
            }
            sb.append(c);
        }
        sb.append('\'');
    }
}