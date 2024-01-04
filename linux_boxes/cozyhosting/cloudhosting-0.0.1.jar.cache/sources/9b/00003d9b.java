package org.hibernate.sql.ast.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/sql/ast/spi/SqlAppender.class */
public interface SqlAppender extends Appendable {
    public static final String NO_SEPARATOR = "";
    public static final String COMA_SEPARATOR = ",";
    public static final char COMA_SEPARATOR_CHAR = ',';
    public static final char WHITESPACE = ' ';
    public static final char OPEN_PARENTHESIS = '(';
    public static final char CLOSE_PARENTHESIS = ')';
    public static final char PARAM_MARKER = '?';
    public static final String NULL_KEYWORD = "null";

    void appendSql(String str);

    default void appendSql(char fragment) {
        appendSql(Character.toString(fragment));
    }

    default void appendSql(int value) {
        appendSql(Integer.toString(value));
    }

    default void appendSql(long value) {
        appendSql(Long.toString(value));
    }

    default void appendSql(boolean value) {
        appendSql(String.valueOf(value));
    }

    @Override // java.lang.Appendable
    default Appendable append(CharSequence csq) {
        appendSql(csq.toString());
        return this;
    }

    @Override // java.lang.Appendable
    default Appendable append(CharSequence csq, int start, int end) {
        appendSql(csq.toString().substring(start, end));
        return this;
    }

    @Override // java.lang.Appendable
    default Appendable append(char c) {
        appendSql(Character.toString(c));
        return this;
    }
}