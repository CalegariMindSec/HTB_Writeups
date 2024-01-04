package org.apache.juli;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-core-10.1.5.jar:org/apache/juli/WebappProperties.class */
public interface WebappProperties {
    String getWebappName();

    String getHostName();

    String getServiceName();

    boolean hasLoggingConfig();
}