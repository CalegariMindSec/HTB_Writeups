package io.micrometer.core.instrument;

import io.micrometer.common.lang.Nullable;
import java.util.function.ToDoubleFunction;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/micrometer-core-1.10.3.jar:io/micrometer/core/instrument/StrongReferenceGaugeFunction.class */
class StrongReferenceGaugeFunction<T> implements ToDoubleFunction<T> {
    @Nullable
    private final T obj;
    private final ToDoubleFunction<T> f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StrongReferenceGaugeFunction(@Nullable T obj, ToDoubleFunction<T> f) {
        this.obj = obj;
        this.f = f;
    }

    @Override // java.util.function.ToDoubleFunction
    public double applyAsDouble(T value) {
        return this.f.applyAsDouble(value);
    }
}