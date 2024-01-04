package org.hibernate.jpa.internal.util;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/jpa/internal/util/PessimisticNumberParser.class */
public final class PessimisticNumberParser {
    private PessimisticNumberParser() {
    }

    public static Integer toNumberOrNull(String parameterName) {
        if (isValidNumber(parameterName)) {
            try {
                return Integer.valueOf(parameterName);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    private static boolean isValidNumber(String parameterName) {
        if (parameterName.length() == 0) {
            return false;
        }
        char firstDigit = parameterName.charAt(0);
        if (Character.isDigit(firstDigit) || '-' == firstDigit || '+' == firstDigit) {
            for (int i = 1; i < parameterName.length(); i++) {
                if (!Character.isDigit(parameterName.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}