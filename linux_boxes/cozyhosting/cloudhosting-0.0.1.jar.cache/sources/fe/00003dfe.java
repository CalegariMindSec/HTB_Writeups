package org.hibernate.sql.ast.tree.from;

@FunctionalInterface
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/sql/ast/tree/from/TableAliasResolver.class */
public interface TableAliasResolver {
    String resolveAlias(String str, String str2);
}