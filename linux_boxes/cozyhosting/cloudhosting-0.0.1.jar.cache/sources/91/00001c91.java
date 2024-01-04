package org.apache.catalina.realm;

import java.security.cert.X509Certificate;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-core-10.1.5.jar:org/apache/catalina/realm/X509UsernameRetriever.class */
public interface X509UsernameRetriever {
    String getUsername(X509Certificate x509Certificate);
}