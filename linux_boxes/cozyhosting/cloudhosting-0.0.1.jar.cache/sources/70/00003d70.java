package org.hibernate.sql;

import org.hibernate.dialect.Dialect;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/sql/Alias.class */
public final class Alias {
    private final int length;
    private final String suffix;

    public Alias(int length, String suffix) {
        this.length = suffix == null ? length : length - suffix.length();
        this.suffix = suffix;
    }

    public Alias(String suffix) {
        this.length = Integer.MAX_VALUE;
        this.suffix = suffix;
    }

    public String toAliasString(String sqlIdentifier) {
        char begin = sqlIdentifier.charAt(0);
        int quoteType = Dialect.QUOTE.indexOf(begin);
        String unquoted = getUnquotedAliasString(sqlIdentifier, quoteType);
        if (quoteType >= 0) {
            char endQuote = Dialect.CLOSED_QUOTE.charAt(quoteType);
            return begin + unquoted + endQuote;
        }
        return unquoted;
    }

    public String toUnquotedAliasString(String sqlIdentifier) {
        return getUnquotedAliasString(sqlIdentifier);
    }

    private String getUnquotedAliasString(String sqlIdentifier) {
        char begin = sqlIdentifier.charAt(0);
        int quoteType = Dialect.QUOTE.indexOf(begin);
        return getUnquotedAliasString(sqlIdentifier, quoteType);
    }

    private String getUnquotedAliasString(String sqlIdentifier, int quoteType) {
        String unquoted = sqlIdentifier;
        if (quoteType >= 0) {
            unquoted = unquoted.substring(1, unquoted.length() - 1);
        }
        if (unquoted.length() > this.length) {
            unquoted = unquoted.substring(0, this.length);
        }
        return this.suffix == null ? unquoted : unquoted + this.suffix;
    }

    public String[] toUnquotedAliasStrings(String[] sqlIdentifiers) {
        String[] aliases = new String[sqlIdentifiers.length];
        for (int i = 0; i < sqlIdentifiers.length; i++) {
            aliases[i] = toUnquotedAliasString(sqlIdentifiers[i]);
        }
        return aliases;
    }

    public String[] toAliasStrings(String[] sqlIdentifiers) {
        String[] aliases = new String[sqlIdentifiers.length];
        for (int i = 0; i < sqlIdentifiers.length; i++) {
            aliases[i] = toAliasString(sqlIdentifiers[i]);
        }
        return aliases;
    }
}