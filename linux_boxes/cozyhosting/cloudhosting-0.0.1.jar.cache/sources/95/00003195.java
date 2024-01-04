package org.hibernate.dialect;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/dialect/MySQLStorageEngine.class */
public interface MySQLStorageEngine {
    boolean supportsCascadeDelete();

    String getTableTypeString(String str);

    boolean hasSelfReferentialForeignKeyBug();

    boolean dropConstraints();
}