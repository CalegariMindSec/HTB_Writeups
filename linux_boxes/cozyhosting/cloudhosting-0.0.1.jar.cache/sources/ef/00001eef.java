package org.apache.logging.log4j.message;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/log4j-api-2.19.0.jar:org/apache/logging/log4j/message/LoggerNameAwareMessage.class */
public interface LoggerNameAwareMessage {
    void setLoggerName(String name);

    String getLoggerName();
}