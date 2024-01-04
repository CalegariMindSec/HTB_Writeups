package org.hibernate.engine.jdbc.spi;

import java.sql.Connection;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/jdbc/spi/SchemaNameResolver.class */
public interface SchemaNameResolver {
    String resolveSchemaName(Connection connection);
}