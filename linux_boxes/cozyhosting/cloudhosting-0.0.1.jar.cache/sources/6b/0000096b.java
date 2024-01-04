package io.micrometer.core.instrument.util;

import ch.qos.logback.core.pattern.color.ANSIConstants;
import ch.qos.logback.core.rolling.helper.DateTokenConverter;
import io.micrometer.core.instrument.binder.BaseUnits;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/micrometer-core-1.10.3.jar:io/micrometer/core/instrument/util/TimeUtils.class */
public final class TimeUtils {
    private static final Pattern PARSE_PATTERN = Pattern.compile("[,_ ]");
    private static final long C0 = 1;
    private static final long C1 = 1000;
    private static final long C2 = 1000000;
    private static final long C3 = 1000000000;
    private static final long C4 = 60000000000L;
    private static final long C5 = 3600000000000L;
    private static final long C6 = 86400000000000L;

    private TimeUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: io.micrometer.core.instrument.util.TimeUtils$1  reason: invalid class name */
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/micrometer-core-1.10.3.jar:io/micrometer/core/instrument/util/TimeUtils$1.class */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$util$concurrent$TimeUnit = new int[TimeUnit.values().length];

        static {
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.NANOSECONDS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MICROSECONDS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MILLISECONDS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MINUTES.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.HOURS.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.DAYS.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public static double convert(double t, TimeUnit sourceUnit, TimeUnit destinationUnit) {
        switch (AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit[sourceUnit.ordinal()]) {
            case 1:
                return nanosToUnit(t, destinationUnit);
            case 2:
                return microsToUnit(t, destinationUnit);
            case 3:
                return millisToUnit(t, destinationUnit);
            case 4:
                return secondsToUnit(t, destinationUnit);
            case 5:
                return minutesToUnit(t, destinationUnit);
            case 6:
                return hoursToUnit(t, destinationUnit);
            case 7:
            default:
                return daysToUnit(t, destinationUnit);
        }
    }

    public static double nanosToUnit(double nanos, TimeUnit destinationUnit) {
        switch (AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit[destinationUnit.ordinal()]) {
            case 1:
            default:
                return nanos;
            case 2:
                return nanos / 1000.0d;
            case 3:
                return nanos / 1000000.0d;
            case 4:
                return nanos / 1.0E9d;
            case 5:
                return nanos / 6.0E10d;
            case 6:
                return nanos / 3.6E12d;
            case 7:
                return nanos / 8.64E13d;
        }
    }

    public static double microsToUnit(double micros, TimeUnit destinationUnit) {
        switch (AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit[destinationUnit.ordinal()]) {
            case 1:
                return micros * 1000.0d;
            case 2:
            default:
                return micros;
            case 3:
                return micros / 1000.0d;
            case 4:
                return micros / 1000000.0d;
            case 5:
                return micros / 6.0E7d;
            case 6:
                return micros / 3.6E9d;
            case 7:
                return micros / 8.64E10d;
        }
    }

    public static double millisToUnit(double millis, TimeUnit destinationUnit) {
        switch (AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit[destinationUnit.ordinal()]) {
            case 1:
                return millis * 1000000.0d;
            case 2:
                return millis * 1000.0d;
            case 3:
            default:
                return millis;
            case 4:
                return millis / 1000.0d;
            case 5:
                return millis / 60000.0d;
            case 6:
                return millis / 3600000.0d;
            case 7:
                return millis / 8.64E7d;
        }
    }

    public static double secondsToUnit(double seconds, TimeUnit destinationUnit) {
        switch (AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit[destinationUnit.ordinal()]) {
            case 1:
                return seconds * 1.0E9d;
            case 2:
                return seconds * 1000000.0d;
            case 3:
                return seconds * 1000.0d;
            case 4:
            default:
                return seconds;
            case 5:
                return seconds / 60.0d;
            case 6:
                return seconds / 3600.0d;
            case 7:
                return seconds / 86400.0d;
        }
    }

    public static double minutesToUnit(double minutes, TimeUnit destinationUnit) {
        switch (AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit[destinationUnit.ordinal()]) {
            case 1:
                return minutes * 6.0E10d;
            case 2:
                return minutes * 6.0E7d;
            case 3:
                return minutes * 60000.0d;
            case 4:
                return minutes * 60.0d;
            case 5:
            default:
                return minutes;
            case 6:
                return minutes / 60.0d;
            case 7:
                return minutes / 1440.0d;
        }
    }

    public static double hoursToUnit(double hours, TimeUnit destinationUnit) {
        switch (AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit[destinationUnit.ordinal()]) {
            case 1:
                return hours * 3.6E12d;
            case 2:
                return hours * 3.6E9d;
            case 3:
                return hours * 3600000.0d;
            case 4:
                return hours * 3600.0d;
            case 5:
                return hours * 60.0d;
            case 6:
            default:
                return hours;
            case 7:
                return hours / 24.0d;
        }
    }

    public static double daysToUnit(double days, TimeUnit destinationUnit) {
        switch (AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit[destinationUnit.ordinal()]) {
            case 1:
                return days * 8.64E13d;
            case 2:
                return days * 8.64E10d;
            case 3:
                return days * 8.64E7d;
            case 4:
                return days * 86400.0d;
            case 5:
                return days * 1440.0d;
            case 6:
                return days * 24.0d;
            case 7:
            default:
                return days;
        }
    }

    @Deprecated
    public static Duration simpleParse(String time) {
        String timeLower = PARSE_PATTERN.matcher(time.toLowerCase()).replaceAll("");
        if (timeLower.endsWith("ns")) {
            return Duration.ofNanos(Long.parseLong(timeLower.substring(0, timeLower.length() - 2)));
        }
        if (timeLower.endsWith(BaseUnits.MILLISECONDS)) {
            return Duration.ofMillis(Long.parseLong(timeLower.substring(0, timeLower.length() - 2)));
        }
        if (timeLower.endsWith("s")) {
            return Duration.ofSeconds(Long.parseLong(timeLower.substring(0, timeLower.length() - 1)));
        }
        if (timeLower.endsWith(ANSIConstants.ESC_END)) {
            return Duration.ofMinutes(Long.parseLong(timeLower.substring(0, timeLower.length() - 1)));
        }
        if (timeLower.endsWith("h")) {
            return Duration.ofHours(Long.parseLong(timeLower.substring(0, timeLower.length() - 1)));
        }
        if (timeLower.endsWith(DateTokenConverter.CONVERTER_KEY)) {
            return Duration.of(Long.parseLong(timeLower.substring(0, timeLower.length() - 1)), ChronoUnit.DAYS);
        }
        throw new DateTimeParseException("Unable to parse " + time + " into duration", timeLower, 0);
    }

    public static String format(Duration duration) {
        int totalSeconds = (int) (duration.toMillis() / 1000);
        int seconds = totalSeconds % 60;
        int totalMinutes = totalSeconds / 60;
        int minutes = totalMinutes % 60;
        int hours = totalMinutes / 60;
        StringBuilder sb = new StringBuilder();
        if (hours > 0) {
            sb.append(hours);
            sb.append('h');
        }
        if (minutes > 0) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append(minutes);
            sb.append('m');
        }
        int nanos = duration.getNano();
        if (seconds > 0 || nanos > 0) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append(seconds);
            if (nanos > 0) {
                sb.append('.');
                sb.append(String.format("%09d", Integer.valueOf(nanos)).replaceFirst("0+$", ""));
            }
            sb.append('s');
        }
        return sb.toString();
    }
}