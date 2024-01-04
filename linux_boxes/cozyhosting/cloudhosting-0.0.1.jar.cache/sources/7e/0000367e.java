package org.hibernate.internal.util;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/QuotingHelper.class */
public final class QuotingHelper {
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !QuotingHelper.class.desiredAssertionStatus();
    }

    private QuotingHelper() {
    }

    public static String unquoteIdentifier(String text) {
        int end = text.length() - 1;
        if ($assertionsDisabled || (text.charAt(0) == '`' && text.charAt(end) == '`')) {
            StringBuilder sb = new StringBuilder(text.length() - 2);
            int i = 1;
            while (i < end) {
                char c = text.charAt(i);
                switch (c) {
                    case '\\':
                        if (i + 1 >= end) {
                            break;
                        } else {
                            i++;
                            char nextChar = text.charAt(i);
                            switch (nextChar) {
                                case '\"':
                                    c = '\"';
                                    continue;
                                case '\'':
                                    c = '\'';
                                    continue;
                                case '\\':
                                    c = '\\';
                                    continue;
                                case '`':
                                    c = '`';
                                    continue;
                                case 'b':
                                    c = '\b';
                                    continue;
                                case 'f':
                                    c = '\f';
                                    continue;
                                case 'n':
                                    c = '\n';
                                    continue;
                                case 'r':
                                    c = '\r';
                                    continue;
                                case 't':
                                    c = '\t';
                                    continue;
                                case 'u':
                                    c = (char) Integer.parseInt(text.substring(i + 1, i + 5), 16);
                                    i += 4;
                                    continue;
                                default:
                                    sb.append('\\');
                                    c = nextChar;
                                    continue;
                            }
                        }
                }
                sb.append(c);
                i++;
            }
            return sb.toString();
        }
        throw new AssertionError();
    }

    public static String unquoteStringLiteral(String text) {
        if ($assertionsDisabled || text.length() > 1) {
            int end = text.length() - 1;
            char delimiter = text.charAt(0);
            if ($assertionsDisabled || delimiter == text.charAt(end)) {
                StringBuilder sb = new StringBuilder(text.length() - 2);
                int i = 1;
                while (i < end) {
                    char c = text.charAt(i);
                    switch (c) {
                        case '\"':
                            if (delimiter != '\"') {
                                break;
                            } else {
                                i++;
                                break;
                            }
                        case '\'':
                            if (delimiter != '\'') {
                                break;
                            } else {
                                i++;
                                break;
                            }
                        case '\\':
                            if (i + 1 >= end) {
                                break;
                            } else {
                                i++;
                                char nextChar = text.charAt(i);
                                switch (nextChar) {
                                    case '\"':
                                        c = '\"';
                                        continue;
                                    case '\'':
                                        c = '\'';
                                        continue;
                                    case '\\':
                                        c = '\\';
                                        continue;
                                    case '`':
                                        c = '`';
                                        continue;
                                    case 'b':
                                        c = '\b';
                                        continue;
                                    case 'f':
                                        c = '\f';
                                        continue;
                                    case 'n':
                                        c = '\n';
                                        continue;
                                    case 'r':
                                        c = '\r';
                                        continue;
                                    case 't':
                                        c = '\t';
                                        continue;
                                    case 'u':
                                        c = (char) Integer.parseInt(text.substring(i + 1, i + 5), 16);
                                        i += 4;
                                        continue;
                                    default:
                                        sb.append('\\');
                                        c = nextChar;
                                        continue;
                                }
                            }
                    }
                    sb.append(c);
                    i++;
                }
                return sb.toString();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }
}