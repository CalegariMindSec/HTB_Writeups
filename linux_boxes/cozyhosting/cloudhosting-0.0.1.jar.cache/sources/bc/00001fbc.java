package org.apache.tomcat.jni;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-core-10.1.5.jar:org/apache/tomcat/jni/CertificateVerifier.class */
public interface CertificateVerifier {
    boolean verify(long j, byte[][] bArr, String str);
}