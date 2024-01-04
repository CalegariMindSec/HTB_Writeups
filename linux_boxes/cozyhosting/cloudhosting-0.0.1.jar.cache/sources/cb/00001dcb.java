package org.apache.coyote;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-core-10.1.5.jar:org/apache/coyote/AsyncContextCallback.class */
public interface AsyncContextCallback {
    void fireOnComplete();

    boolean isAvailable();

    void incrementInProgressAsyncCount();

    void decrementInProgressAsyncCount();
}