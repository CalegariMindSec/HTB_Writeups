package org.hibernate.engine.jdbc;

import java.io.InputStream;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/jdbc/BinaryStream.class */
public interface BinaryStream {
    InputStream getInputStream();

    byte[] getBytes();

    long getLength();

    void release();
}