package org.apache.el.parser;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-el-10.1.5.jar:org/apache/el/parser/TokenMgrError.class */
public class TokenMgrError extends Error {
    private static final long serialVersionUID = 1;
    public static final int LEXICAL_ERROR = 0;
    public static final int STATIC_LEXER_ERROR = 1;
    public static final int INVALID_LEXICAL_STATE = 2;
    public static final int LOOP_DETECTED = 3;
    int errorCode;

    protected static final String addEscapes(String str) {
        StringBuilder retval = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case '\b':
                    retval.append("\\b");
                    break;
                case '\t':
                    retval.append("\\t");
                    break;
                case '\n':
                    retval.append("\\n");
                    break;
                case '\f':
                    retval.append("\\f");
                    break;
                case '\r':
                    retval.append("\\r");
                    break;
                case '\"':
                    retval.append("\\\"");
                    break;
                case '\'':
                    retval.append("\\'");
                    break;
                case '\\':
                    retval.append("\\\\");
                    break;
                default:
                    char ch2 = str.charAt(i);
                    if (ch2 < ' ' || ch2 > '~') {
                        String s = "0000" + Integer.toString(ch2, 16);
                        retval.append("\\u" + s.substring(s.length() - 4));
                        break;
                    } else {
                        retval.append(ch2);
                        break;
                    }
                    break;
            }
        }
        return retval.toString();
    }

    protected static String LexicalErr(boolean EOFSeen, int lexState, int errorLine, int errorColumn, String errorAfter, int curChar) {
        char curChar1 = (char) curChar;
        return "Lexical error at line " + errorLine + ", column " + errorColumn + ".  Encountered: " + (EOFSeen ? "<EOF> " : "\"" + addEscapes(String.valueOf(curChar1)) + "\" (" + curChar + "), ") + "after : \"" + addEscapes(errorAfter) + "\"";
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage();
    }

    public TokenMgrError() {
    }

    public TokenMgrError(String message, int reason) {
        super(message);
        this.errorCode = reason;
    }

    public TokenMgrError(boolean EOFSeen, int lexState, int errorLine, int errorColumn, String errorAfter, int curChar, int reason) {
        this(LexicalErr(EOFSeen, lexState, errorLine, errorColumn, errorAfter, curChar), reason);
    }
}