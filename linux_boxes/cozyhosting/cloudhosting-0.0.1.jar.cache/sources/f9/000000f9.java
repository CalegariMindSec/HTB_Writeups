package ch.qos.logback.core.html;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/logback-core-1.4.5.jar:ch/qos/logback/core/html/IThrowableRenderer.class */
public interface IThrowableRenderer<E> {
    void render(StringBuilder sb, E e);
}