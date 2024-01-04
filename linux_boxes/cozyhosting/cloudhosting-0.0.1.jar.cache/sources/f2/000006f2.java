package com.sun.activation.registries;

import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/angus-activation-1.0.0.jar:com/sun/activation/registries/LogSupport.class */
class LogSupport {
    private static boolean debug;
    private static Logger logger;
    private static final Level level = Level.FINE;

    static {
        debug = false;
        try {
            debug = Boolean.getBoolean("jakarta.activation.debug") || Boolean.getBoolean("javax.activation.debug");
        } catch (Throwable th) {
        }
        logger = Logger.getLogger("jakarta.activation");
    }

    private LogSupport() {
    }

    public static void log(String msg) {
        if (debug) {
            System.out.println(msg);
        }
        logger.log(level, msg);
    }

    public static void log(String msg, Throwable t) {
        if (debug) {
            System.out.println(msg + "; Exception: " + t);
        }
        logger.log(level, msg, t);
    }

    public static boolean isLoggable() {
        return debug || logger.isLoggable(level);
    }
}