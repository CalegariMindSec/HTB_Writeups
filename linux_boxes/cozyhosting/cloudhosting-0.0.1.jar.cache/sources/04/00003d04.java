package org.hibernate.resource.jdbc;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.NClob;
import java.sql.ResultSet;
import java.sql.Statement;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/resource/jdbc/ResourceRegistry.class */
public interface ResourceRegistry {
    boolean hasRegisteredResources();

    void releaseResources();

    void register(Statement statement, boolean z);

    void release(Statement statement);

    void register(ResultSet resultSet, Statement statement);

    void release(ResultSet resultSet, Statement statement);

    void register(Blob blob);

    void release(Blob blob);

    void register(Clob clob);

    void release(Clob clob);

    void register(NClob nClob);

    void release(NClob nClob);

    void cancelLastQuery();
}