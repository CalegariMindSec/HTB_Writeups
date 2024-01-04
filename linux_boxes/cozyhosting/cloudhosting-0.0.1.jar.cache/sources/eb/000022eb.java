package org.aspectj.bridge;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/bridge/IProgressListener.class */
public interface IProgressListener {
    void setText(String str);

    void setProgress(double d);

    void setCancelledRequested(boolean z);

    boolean isCancelledRequested();
}