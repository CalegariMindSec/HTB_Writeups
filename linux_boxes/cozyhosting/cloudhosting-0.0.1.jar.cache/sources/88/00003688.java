package org.hibernate.internal.util;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Comparator;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/ZonedDateTimeComparator.class */
public class ZonedDateTimeComparator implements Comparator<ZonedDateTime>, Serializable {
    public static final Comparator<ZonedDateTime> INSTANCE = new ZonedDateTimeComparator();

    @Override // java.util.Comparator
    public int compare(ZonedDateTime one, ZonedDateTime another) {
        return one.toInstant().compareTo(another.toInstant());
    }
}