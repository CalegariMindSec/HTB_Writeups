package org.apache.catalina;

import java.security.Principal;
import java.util.Enumeration;
import org.ietf.jgss.GSSCredential;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-core-10.1.5.jar:org/apache/catalina/TomcatPrincipal.class */
public interface TomcatPrincipal extends Principal {
    Principal getUserPrincipal();

    GSSCredential getGssCredential();

    void logout() throws Exception;

    Object getAttribute(String str);

    Enumeration<String> getAttributeNames();
}