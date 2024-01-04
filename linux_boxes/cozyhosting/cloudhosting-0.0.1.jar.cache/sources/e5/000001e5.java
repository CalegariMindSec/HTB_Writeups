package ch.qos.logback.core.pattern.parser;

import java.util.List;
import org.springframework.aop.framework.autoproxy.target.QuickTargetSourceCreator;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/logback-core-1.4.5.jar:ch/qos/logback/core/pattern/parser/Token.class */
class Token {
    static final int PERCENT = 37;
    static final int RIGHT_PARENTHESIS = 41;
    static final int MINUS = 45;
    static final int DOT = 46;
    static final int CURLY_LEFT = 123;
    static final int CURLY_RIGHT = 125;
    static final int LITERAL = 1000;
    static final int FORMAT_MODIFIER = 1002;
    static final int SIMPLE_KEYWORD = 1004;
    static final int COMPOSITE_KEYWORD = 1005;
    static final int OPTION = 1006;
    static final int EOF = Integer.MAX_VALUE;
    static Token EOF_TOKEN = new Token(Integer.MAX_VALUE, "EOF");
    static Token RIGHT_PARENTHESIS_TOKEN = new Token(41);
    static Token BARE_COMPOSITE_KEYWORD_TOKEN = new Token(1005, "BARE");
    static Token PERCENT_TOKEN = new Token(37);
    private final int type;
    private final String value;
    private final List<String> optionsList;

    public Token(int type) {
        this(type, null, null);
    }

    public Token(int type, String value) {
        this(type, value, null);
    }

    public Token(int type, List<String> optionsList) {
        this(type, null, optionsList);
    }

    public Token(int type, String value, List<String> optionsList) {
        this.type = type;
        this.value = value;
        this.optionsList = optionsList;
    }

    public int getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public List<String> getOptionsList() {
        return this.optionsList;
    }

    public String toString() {
        String typeStr;
        switch (this.type) {
            case 37:
                typeStr = QuickTargetSourceCreator.PREFIX_THREAD_LOCAL;
                break;
            case 41:
                typeStr = "RIGHT_PARENTHESIS";
                break;
            case 1000:
                typeStr = "LITERAL";
                break;
            case 1002:
                typeStr = "FormatModifier";
                break;
            case SIMPLE_KEYWORD /* 1004 */:
                typeStr = "SIMPLE_KEYWORD";
                break;
            case 1005:
                typeStr = "COMPOSITE_KEYWORD";
                break;
            case OPTION /* 1006 */:
                typeStr = "OPTION";
                break;
            default:
                typeStr = "UNKNOWN";
                break;
        }
        if (this.value == null) {
            return "Token(" + typeStr + ")";
        }
        return "Token(" + typeStr + ", \"" + this.value + "\")";
    }

    public int hashCode() {
        int result = this.type;
        return (29 * result) + (this.value != null ? this.value.hashCode() : 0);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Token)) {
            return false;
        }
        Token token = (Token) o;
        if (this.type != token.type) {
            return false;
        }
        if (this.value != null) {
            if (!this.value.equals(token.value)) {
                return false;
            }
            return true;
        } else if (token.value != null) {
            return false;
        } else {
            return true;
        }
    }
}