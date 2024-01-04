package jakarta.servlet;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-core-10.1.5.jar:jakarta/servlet/ServletConnection.class */
public interface ServletConnection {
    String getConnectionId();

    String getProtocol();

    String getProtocolConnectionId();

    boolean isSecure();
}