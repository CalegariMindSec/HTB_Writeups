package org.hibernate.sql.ast.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/sql/ast/spi/SqlAliasBase.class */
public interface SqlAliasBase {
    String getAliasStem();

    String generateNewAlias();
}