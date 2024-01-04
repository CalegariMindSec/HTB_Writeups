package org.hibernate.sql.ast;

import org.hibernate.Incubating;

@Incubating
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/sql/ast/Clause.class */
public enum Clause {
    INSERT,
    VALUES,
    UPDATE,
    SET,
    DELETE,
    SELECT,
    FROM,
    WHERE,
    GROUP,
    HAVING,
    ORDER,
    OFFSET,
    FETCH,
    FOR_UPDATE,
    OVER,
    WITHIN_GROUP,
    PARTITION,
    CALL,
    IRRELEVANT
}