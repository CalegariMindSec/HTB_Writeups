package org.apache.catalina.util;

import org.apache.tomcat.util.net.SSLSupport;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-core-10.1.5.jar:org/apache/catalina/util/TLSUtil.class */
public class TLSUtil {
    public static boolean isTLSRequestAttribute(String name) {
        boolean z = true;
        switch (name.hashCode()) {
            case -2122667291:
                if (name.equals("jakarta.servlet.request.key_size")) {
                    z = true;
                    break;
                }
                break;
            case -2026697288:
                if (name.equals("jakarta.servlet.request.ssl_session_mgr")) {
                    z = true;
                    break;
                }
                break;
            case -1850447920:
                if (name.equals(SSLSupport.PROTOCOL_VERSION_KEY)) {
                    z = true;
                    break;
                }
                break;
            case -1831323950:
                if (name.equals(SSLSupport.REQUESTED_PROTOCOL_VERSIONS_KEY)) {
                    z = true;
                    break;
                }
                break;
            case -493727176:
                if (name.equals(SSLSupport.REQUESTED_CIPHERS_KEY)) {
                    z = true;
                    break;
                }
                break;
            case 660520589:
                if (name.equals("jakarta.servlet.request.X509Certificate")) {
                    z = false;
                    break;
                }
                break;
            case 1396315848:
                if (name.equals("jakarta.servlet.request.cipher_suite")) {
                    z = true;
                    break;
                }
                break;
            case 1597190523:
                if (name.equals("jakarta.servlet.request.ssl_session_id")) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
            case true:
            case true:
            case true:
            case true:
            case true:
            case true:
            case true:
                return true;
            default:
                return false;
        }
    }
}