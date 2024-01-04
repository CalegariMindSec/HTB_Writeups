package org.hibernate.internal.util;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/ValueHolder.class */
public class ValueHolder<T> {
    private final DeferredInitializer<T> valueInitializer;
    private T value;
    private static final DeferredInitializer NO_DEFERRED_INITIALIZER = new DeferredInitializer() { // from class: org.hibernate.internal.util.ValueHolder.1
        @Override // org.hibernate.internal.util.ValueHolder.DeferredInitializer
        public Void initialize() {
            return null;
        }
    };

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/ValueHolder$DeferredInitializer.class */
    public interface DeferredInitializer<T> {
        T initialize();
    }

    public ValueHolder(DeferredInitializer<T> valueInitializer) {
        this.valueInitializer = valueInitializer;
    }

    public ValueHolder(T value) {
        this(NO_DEFERRED_INITIALIZER);
        this.value = value;
    }

    public T getValue() {
        if (this.value == null) {
            this.value = this.valueInitializer.initialize();
        }
        return this.value;
    }
}