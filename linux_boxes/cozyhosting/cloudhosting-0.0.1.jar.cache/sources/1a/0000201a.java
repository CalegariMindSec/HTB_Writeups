package org.apache.tomcat.util.descriptor;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-core-10.1.5.jar:org/apache/tomcat/util/descriptor/Constants.class */
public class Constants {
    public static final String PACKAGE_NAME = Constants.class.getPackage().getName();
    public static final boolean IS_SECURITY_ENABLED;

    static {
        IS_SECURITY_ENABLED = System.getSecurityManager() != null;
    }
}