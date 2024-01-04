package org.hibernate.id;

import java.sql.ResultSet;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/id/ResultSetIdentifierConsumer.class */
public interface ResultSetIdentifierConsumer {
    Object consumeIdentifier(ResultSet resultSet);
}