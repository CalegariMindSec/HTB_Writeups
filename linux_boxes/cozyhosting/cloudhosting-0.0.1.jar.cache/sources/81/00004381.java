package org.postgresql.jdbc2;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/jdbc2/ArrayAssistant.class */
public interface ArrayAssistant {
    Class<?> baseType();

    Object buildElement(byte[] bArr, int i, int i2);

    Object buildElement(String str);
}