package org.hibernate.internal.util;

import java.util.function.Supplier;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/LazyValue.class */
public class LazyValue<T> {
    public static final Object NULL = new Object();
    private final Supplier<T> supplier;
    private Object value;

    public LazyValue(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public Object getValue() {
        if (this.value == null) {
            T obtainedValue = this.supplier.get();
            if (obtainedValue == null) {
                this.value = NULL;
            } else {
                this.value = obtainedValue;
            }
        }
        if (this.value == NULL) {
            return null;
        }
        return this.value;
    }
}