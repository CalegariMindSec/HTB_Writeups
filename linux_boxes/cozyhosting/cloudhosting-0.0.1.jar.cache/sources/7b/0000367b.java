package org.hibernate.internal.util;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/MutableObject.class */
public class MutableObject<T> {
    private T reference;

    public T get() {
        return this.reference;
    }

    public boolean isSet() {
        return this.reference != null;
    }

    public boolean isNotSet() {
        return this.reference == null;
    }

    public void set(T reference) {
        this.reference = reference;
    }

    public void set(T reference, Consumer<T> existingConsumer) {
        if (this.reference != null) {
            existingConsumer.accept(this.reference);
        }
        this.reference = reference;
    }

    public void set(T reference, BiConsumer<T, T> existingConsumer) {
        if (this.reference != null) {
            existingConsumer.accept(reference, this.reference);
        }
        this.reference = reference;
    }

    public void setIfNot(T reference) {
        if (this.reference == null) {
            this.reference = reference;
        }
    }

    public void setIfNot(T reference, Supplier<RuntimeException> overwriteHandler) {
        if (this.reference == null) {
            this.reference = reference;
            return;
        }
        throw overwriteHandler.get();
    }

    public void setIfNot(Supplier<T> referenceSupplier) {
        if (this.reference == null) {
            this.reference = referenceSupplier.get();
        }
    }

    public void setIfNot(Supplier<T> referenceSupplier, Supplier<RuntimeException> overwriteHandler) {
        if (this.reference == null) {
            this.reference = referenceSupplier.get();
            return;
        }
        throw overwriteHandler.get();
    }
}