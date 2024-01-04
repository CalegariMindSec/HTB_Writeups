package org.hibernate.stat;

import java.io.Serializable;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/stat/QueryStatistics.class */
public interface QueryStatistics extends Serializable {
    long getExecutionCount();

    long getExecutionRowCount();

    long getExecutionAvgTime();

    long getExecutionMaxTime();

    long getExecutionMinTime();

    long getExecutionTotalTime();

    double getExecutionAvgTimeAsDouble();

    long getCacheHitCount();

    long getCacheMissCount();

    long getCachePutCount();

    default long getPlanCacheHitCount() {
        return 0L;
    }

    default long getPlanCacheMissCount() {
        return 0L;
    }

    default long getPlanCompilationTotalMicroseconds() {
        return 0L;
    }
}