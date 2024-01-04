package org.apache.catalina;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-core-10.1.5.jar:org/apache/catalina/ThreadBindingListener.class */
public interface ThreadBindingListener {
    void bind();

    void unbind();
}