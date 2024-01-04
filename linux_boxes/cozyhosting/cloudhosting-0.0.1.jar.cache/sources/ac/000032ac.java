package org.hibernate.engine.jdbc;

import java.io.Reader;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/jdbc/CharacterStream.class */
public interface CharacterStream {
    Reader asReader();

    String asString();

    long getLength();

    void release();
}