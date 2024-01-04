package org.apache.logging.log4j.internal;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/log4j-api-2.19.0.jar:org/apache/logging/log4j/internal/LogManagerStatus.class */
public class LogManagerStatus {
    private static boolean initialized = false;

    public static void setInitialized(boolean managerStatus) {
        initialized = managerStatus;
    }

    public static boolean isInitialized() {
        return initialized;
    }
}