package org.hibernate.spi;

import java.util.function.BiFunction;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/spi/DotIdentifierSequence.class */
public interface DotIdentifierSequence {
    DotIdentifierSequence getParent();

    String getLocalName();

    String getFullPath();

    DotIdentifierSequence append(String str);

    default boolean isRoot() {
        return getParent() == null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    default <T> T resolve(T base, BiFunction<T, String, T> baseResolver, BiFunction<T, String, T> resolver) {
        T result;
        if (getParent() == null) {
            result = baseResolver.apply(base, getLocalName());
        } else {
            result = resolver.apply(getParent().resolve(base, baseResolver, resolver), getLocalName());
        }
        return result;
    }
}