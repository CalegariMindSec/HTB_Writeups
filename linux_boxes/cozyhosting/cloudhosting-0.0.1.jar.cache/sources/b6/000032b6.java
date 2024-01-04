package org.hibernate.engine.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/jdbc/ReaderInputStream.class */
public class ReaderInputStream extends InputStream {
    private final Reader reader;

    public ReaderInputStream(Reader reader) {
        this.reader = reader;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return this.reader.read();
    }
}