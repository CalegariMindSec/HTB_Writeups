package org.hibernate.dialect;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/dialect/GroupByConstantRenderingStrategy.class */
public enum GroupByConstantRenderingStrategy {
    EMPTY_GROUPING,
    CONSTANT,
    CONSTANT_EXPRESSION,
    SUBQUERY,
    COLUMN_REFERENCE
}