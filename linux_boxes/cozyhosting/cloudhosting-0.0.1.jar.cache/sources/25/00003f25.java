package org.hibernate.stat.internal;

import java.io.Serializable;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/stat/internal/CategorizedStatistics.class */
public class CategorizedStatistics implements Serializable {
    private final String categoryName;

    CategorizedStatistics(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return this.categoryName;
    }
}