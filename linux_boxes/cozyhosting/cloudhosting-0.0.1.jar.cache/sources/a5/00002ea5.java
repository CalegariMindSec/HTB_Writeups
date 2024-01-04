package org.hibernate.boot.model.source.spi;

import java.util.Map;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/model/source/spi/FilterSource.class */
public interface FilterSource {
    String getName();

    String getCondition();

    boolean shouldAutoInjectAliases();

    Map<String, String> getAliasToTableMap();

    Map<String, String> getAliasToEntityMap();
}