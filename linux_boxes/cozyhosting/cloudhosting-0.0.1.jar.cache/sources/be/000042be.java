package org.postgresql.core;

import java.io.IOException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/core/PGBindException.class */
public class PGBindException extends IOException {
    private final IOException ioe;

    public PGBindException(IOException ioe) {
        this.ioe = ioe;
    }

    public IOException getIOException() {
        return this.ioe;
    }
}