package org.apache.catalina.loader;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-core-10.1.5.jar:org/apache/catalina/loader/ResourceEntry.class */
public class ResourceEntry {
    public long lastModified = -1;
    public volatile Class<?> loadedClass = null;
}