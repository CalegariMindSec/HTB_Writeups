package org.aspectj.weaver.tools;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/weaver/tools/Trace.class */
public interface Trace {
    void enter(String str, Object obj, Object[] objArr);

    void enter(String str, Object obj);

    void exit(String str, Object obj);

    void exit(String str, Throwable th);

    void exit(String str);

    void event(String str);

    void event(String str, Object obj, Object[] objArr);

    void debug(String str);

    void info(String str);

    void warn(String str);

    void warn(String str, Throwable th);

    void error(String str);

    void error(String str, Throwable th);

    void fatal(String str);

    void fatal(String str, Throwable th);

    void enter(String str, Object obj, Object obj2);

    void enter(String str, Object obj, boolean z);

    void exit(String str, boolean z);

    void exit(String str, int i);

    void event(String str, Object obj, Object obj2);

    boolean isTraceEnabled();

    void setTraceEnabled(boolean z);
}