package org.hibernate.cfg;

@Deprecated
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/cfg/NamingStrategy.class */
public interface NamingStrategy {
    String classToTableName(String str);

    String propertyToColumnName(String str);

    String tableName(String str);

    String columnName(String str);

    String collectionTableName(String str, String str2, String str3, String str4, String str5);

    String joinKeyColumnName(String str, String str2);

    String foreignKeyColumnName(String str, String str2, String str3, String str4);

    String logicalColumnName(String str, String str2);

    String logicalCollectionTableName(String str, String str2, String str3, String str4);

    String logicalCollectionColumnName(String str, String str2, String str3);
}