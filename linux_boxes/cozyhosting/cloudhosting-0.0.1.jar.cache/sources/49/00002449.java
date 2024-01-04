package org.aspectj.weaver;

import java.lang.ref.WeakReference;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/weaver/WeakClassLoaderReference.class */
public class WeakClassLoaderReference {
    protected final int hashcode;
    private final WeakReference<ClassLoader> loaderRef;

    public WeakClassLoaderReference(ClassLoader loader) {
        this.loaderRef = new WeakReference<>(loader);
        if (loader == null) {
            this.hashcode = System.identityHashCode(this);
        } else {
            this.hashcode = loader.hashCode() * 37;
        }
    }

    public ClassLoader getClassLoader() {
        ClassLoader instance = this.loaderRef.get();
        return instance;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof WeakClassLoaderReference)) {
            return false;
        }
        WeakClassLoaderReference other = (WeakClassLoaderReference) obj;
        return other.hashcode == this.hashcode;
    }

    public int hashCode() {
        return this.hashcode;
    }
}