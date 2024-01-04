package org.hibernate.boot.model.source.spi;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/model/source/spi/PluralAttributeNature.class */
public enum PluralAttributeNature {
    BAG(Collection.class, false),
    ID_BAG(Collection.class, false),
    SET(Set.class, false),
    LIST(List.class, true),
    ARRAY(Object[].class, true),
    MAP(Map.class, true);
    
    private final boolean indexed;
    private final Class<?> reportedJavaType;

    PluralAttributeNature(Class cls, boolean indexed) {
        this.reportedJavaType = cls;
        this.indexed = indexed;
    }

    public Class<?> reportedJavaType() {
        return this.reportedJavaType;
    }

    public boolean isIndexed() {
        return this.indexed;
    }
}