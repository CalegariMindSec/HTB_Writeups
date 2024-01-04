package org.hibernate.engine.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.NClob;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/jdbc/LobCreator.class */
public interface LobCreator {
    Blob wrap(Blob blob);

    Clob wrap(Clob clob);

    NClob wrap(NClob nClob);

    Blob createBlob(byte[] bArr);

    Blob createBlob(InputStream inputStream, long j);

    Clob createClob(String str);

    Clob createClob(Reader reader, long j);

    NClob createNClob(String str);

    NClob createNClob(Reader reader, long j);
}