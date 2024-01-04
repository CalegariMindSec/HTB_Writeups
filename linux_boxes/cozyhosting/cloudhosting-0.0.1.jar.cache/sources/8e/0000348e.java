package org.hibernate.exception.spi;

import java.sql.SQLException;

@FunctionalInterface
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/exception/spi/ViolatedConstraintNameExtractor.class */
public interface ViolatedConstraintNameExtractor {
    String extractConstraintName(SQLException sQLException);
}