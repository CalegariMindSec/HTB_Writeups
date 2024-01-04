package org.hibernate.jpa.spi;

import java.util.Map;

@Deprecated(since = "6.0")
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/jpa/spi/IdentifierGeneratorStrategyProvider.class */
public interface IdentifierGeneratorStrategyProvider {
    Map<String, Class<?>> getStrategies();
}