package org.apache.catalina.security;

import java.security.BasicPermission;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-core-10.1.5.jar:org/apache/catalina/security/DeployXmlPermission.class */
public class DeployXmlPermission extends BasicPermission {
    private static final long serialVersionUID = 1;

    public DeployXmlPermission(String name) {
        super(name);
    }

    public DeployXmlPermission(String name, String actions) {
        super(name, actions);
    }
}