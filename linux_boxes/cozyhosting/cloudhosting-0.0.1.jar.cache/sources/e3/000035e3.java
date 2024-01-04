package org.hibernate.id;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/id/IntegralDataTypeHolder.class */
public interface IntegralDataTypeHolder extends Serializable {
    IntegralDataTypeHolder initialize(long j);

    IntegralDataTypeHolder initialize(ResultSet resultSet, long j) throws SQLException;

    void bind(PreparedStatement preparedStatement, int i) throws SQLException;

    IntegralDataTypeHolder increment();

    IntegralDataTypeHolder add(long j);

    IntegralDataTypeHolder decrement();

    IntegralDataTypeHolder subtract(long j);

    IntegralDataTypeHolder multiplyBy(IntegralDataTypeHolder integralDataTypeHolder);

    IntegralDataTypeHolder multiplyBy(long j);

    boolean eq(IntegralDataTypeHolder integralDataTypeHolder);

    boolean eq(long j);

    boolean lt(IntegralDataTypeHolder integralDataTypeHolder);

    boolean lt(long j);

    boolean gt(IntegralDataTypeHolder integralDataTypeHolder);

    boolean gt(long j);

    IntegralDataTypeHolder copy();

    Number makeValue();

    Number makeValueThenIncrement();

    Number makeValueThenAdd(long j);
}