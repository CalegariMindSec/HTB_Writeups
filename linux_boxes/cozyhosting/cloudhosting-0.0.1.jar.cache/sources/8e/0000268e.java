package org.checkerframework.checker.i18nformatter.qual;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.thymeleaf.spring6.processor.SpringInputGeneralFieldTagProcessor;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/checker-qual-3.5.0.jar:org/checkerframework/checker/i18nformatter/qual/I18nConversionCategory.class */
public enum I18nConversionCategory {
    UNUSED(null, null),
    GENERAL(null, null),
    DATE(new Class[]{Date.class, Number.class}, new String[]{SpringInputGeneralFieldTagProcessor.DATE_INPUT_TYPE_ATTR_VALUE, SpringInputGeneralFieldTagProcessor.TIME_INPUT_TYPE_ATTR_VALUE}),
    NUMBER(new Class[]{Number.class}, new String[]{SpringInputGeneralFieldTagProcessor.NUMBER_INPUT_TYPE_ATTR_VALUE, "choice"});
    
    public final Class<? extends Object>[] types;
    public final String[] strings;
    static I18nConversionCategory[] namedCategories = {DATE, NUMBER};

    I18nConversionCategory(Class[] clsArr, String[] strings) {
        this.types = clsArr;
        this.strings = strings;
    }

    public static I18nConversionCategory stringToI18nConversionCategory(String string) {
        I18nConversionCategory[] i18nConversionCategoryArr;
        String[] strArr;
        String string2 = string.toLowerCase();
        for (I18nConversionCategory v : namedCategories) {
            for (String s : v.strings) {
                if (s.equals(string2)) {
                    return v;
                }
            }
        }
        throw new IllegalArgumentException("Invalid format type " + string2);
    }

    private static <E> Set<E> arrayToSet(E[] a) {
        return new HashSet(Arrays.asList(a));
    }

    public static boolean isSubsetOf(I18nConversionCategory a, I18nConversionCategory b) {
        return intersect(a, b) == a;
    }

    public static I18nConversionCategory intersect(I18nConversionCategory a, I18nConversionCategory b) {
        I18nConversionCategory[] i18nConversionCategoryArr;
        if (a == UNUSED) {
            return b;
        }
        if (b == UNUSED) {
            return a;
        }
        if (a == GENERAL) {
            return b;
        }
        if (b == GENERAL) {
            return a;
        }
        Set<Class<? extends Object>> as = arrayToSet(a.types);
        Set<Class<? extends Object>> bs = arrayToSet(b.types);
        as.retainAll(bs);
        for (I18nConversionCategory v : new I18nConversionCategory[]{DATE, NUMBER}) {
            Set<Class<? extends Object>> vs = arrayToSet(v.types);
            if (vs.equals(as)) {
                return v;
            }
        }
        throw new RuntimeException();
    }

    public static I18nConversionCategory union(I18nConversionCategory a, I18nConversionCategory b) {
        if (a == UNUSED || b == UNUSED) {
            return UNUSED;
        }
        if (a == GENERAL || b == GENERAL) {
            return GENERAL;
        }
        if (a == DATE || b == DATE) {
            return DATE;
        }
        return NUMBER;
    }

    @Override // java.lang.Enum
    public String toString() {
        Class<? extends Object>[] clsArr;
        StringBuilder sb = new StringBuilder(name());
        if (this.types == null) {
            sb.append(" conversion category (all types)");
        } else {
            sb.append(" conversion category (one of: ");
            boolean first = true;
            for (Class<? extends Object> cls : this.types) {
                if (!first) {
                    sb.append(", ");
                }
                sb.append(cls.getCanonicalName());
                first = false;
            }
            sb.append(")");
        }
        return sb.toString();
    }
}