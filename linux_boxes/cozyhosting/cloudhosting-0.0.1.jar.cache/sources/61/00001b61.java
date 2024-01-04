package org.apache.catalina;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-core-10.1.5.jar:org/apache/catalina/SessionIdGenerator.class */
public interface SessionIdGenerator {
    String getJvmRoute();

    void setJvmRoute(String str);

    int getSessionIdLength();

    void setSessionIdLength(int i);

    String generateSessionId();

    String generateSessionId(String str);
}