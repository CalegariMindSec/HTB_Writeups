package io.micrometer.core.instrument;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/micrometer-core-1.10.3.jar:io/micrometer/core/instrument/Statistic.class */
public enum Statistic {
    TOTAL("total"),
    TOTAL_TIME("total"),
    COUNT("count"),
    MAX("max"),
    VALUE("value"),
    UNKNOWN("unknown"),
    ACTIVE_TASKS("active"),
    DURATION("duration");
    
    private final String tagValueRepresentation;

    Statistic(String tagValueRepresentation) {
        this.tagValueRepresentation = tagValueRepresentation;
    }

    public String getTagValueRepresentation() {
        return this.tagValueRepresentation;
    }
}