package org.hibernate.id.insert;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/id/insert/Binder.class */
public interface Binder {
    void bindValues(PreparedStatement preparedStatement) throws SQLException;

    Object getEntity();
}