package jakarta.security.auth.message;

import java.util.Map;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-core-10.1.5.jar:jakarta/security/auth/message/MessageInfo.class */
public interface MessageInfo {
    Object getRequestMessage();

    Object getResponseMessage();

    void setRequestMessage(Object obj);

    void setResponseMessage(Object obj);

    Map<String, Object> getMap();
}