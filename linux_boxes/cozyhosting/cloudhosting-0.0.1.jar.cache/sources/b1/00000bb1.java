package jakarta.websocket;

import java.util.List;
import java.util.Map;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-websocket-10.1.5.jar:jakarta/websocket/HandshakeResponse.class */
public interface HandshakeResponse {
    public static final String SEC_WEBSOCKET_ACCEPT = "Sec-WebSocket-Accept";

    Map<String, List<String>> getHeaders();
}