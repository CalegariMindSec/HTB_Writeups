package org.hibernate.boot.jaxb.hbm.spi;

import java.util.List;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/ResultSetMappingBindingDefinition.class */
public interface ResultSetMappingBindingDefinition {
    String getName();

    List getValueMappingSources();
}