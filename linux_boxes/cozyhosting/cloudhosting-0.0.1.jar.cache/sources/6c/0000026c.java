package ch.qos.logback.core.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/logback-core-1.4.5.jar:ch/qos/logback/core/util/CachingDateFormatter.class */
public class CachingDateFormatter {
    final DateTimeFormatter dtf;
    final ZoneId zoneId;
    final AtomicReference<CacheTuple> atomicReference;

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/logback-core-1.4.5.jar:ch/qos/logback/core/util/CachingDateFormatter$CacheTuple.class */
    static class CacheTuple {
        final long lastTimestamp;
        final String cachedStr;

        public CacheTuple(long lastTimestamp, String cachedStr) {
            this.lastTimestamp = lastTimestamp;
            this.cachedStr = cachedStr;
        }
    }

    public CachingDateFormatter(String pattern) {
        this(pattern, null);
    }

    public CachingDateFormatter(String pattern, ZoneId aZoneId) {
        if (aZoneId == null) {
            this.zoneId = ZoneId.systemDefault();
        } else {
            this.zoneId = aZoneId;
        }
        this.dtf = DateTimeFormatter.ofPattern(pattern).withZone(this.zoneId);
        CacheTuple cacheTuple = new CacheTuple(-1L, null);
        this.atomicReference = new AtomicReference<>(cacheTuple);
    }

    public final String format(long now) {
        CacheTuple localCacheTuple = this.atomicReference.get();
        if (now != localCacheTuple.lastTimestamp) {
            Instant instant = Instant.ofEpochMilli(now);
            String result = this.dtf.format(instant);
            localCacheTuple = new CacheTuple(now, result);
            this.atomicReference.compareAndSet(localCacheTuple, localCacheTuple);
        }
        return localCacheTuple.cachedStr;
    }
}