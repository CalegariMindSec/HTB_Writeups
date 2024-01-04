package org.hibernate.internal.util.compare;

import java.util.Calendar;
import java.util.Comparator;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/compare/CalendarComparator.class */
public class CalendarComparator implements Comparator<Calendar> {
    public static final CalendarComparator INSTANCE = new CalendarComparator();

    @Override // java.util.Comparator
    public int compare(Calendar x, Calendar y) {
        if (x.before(y)) {
            return -1;
        }
        if (x.after(y)) {
            return 1;
        }
        return 0;
    }
}