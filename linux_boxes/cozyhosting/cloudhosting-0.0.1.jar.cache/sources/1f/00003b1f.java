package org.hibernate.query.sql.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/query/sql/spi/ParameterRecognizer.class */
public interface ParameterRecognizer {
    void ordinalParameter(int i);

    void namedParameter(String str, int i);

    void jpaPositionalParameter(int i, int i2);

    void other(char c);

    default void complete() {
    }
}