package org.glassfish.jaxb.runtime.api;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jaxb-runtime-4.0.1.jar:org/glassfish/jaxb/runtime/api/RawAccessor.class */
public abstract class RawAccessor<B, V> {
    public abstract V get(B b) throws AccessorException;

    public abstract void set(B b, V v) throws AccessorException;
}