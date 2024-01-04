package org.hibernate.engine.jdbc.env.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/jdbc/env/spi/NameQualifierSupport.class */
public enum NameQualifierSupport {
    CATALOG,
    SCHEMA,
    BOTH,
    NONE;

    public boolean supportsCatalogs() {
        return this == CATALOG || this == BOTH;
    }

    public boolean supportsSchemas() {
        return this == SCHEMA || this == BOTH;
    }
}