package org.postgresql.shaded.com.ongres.scram.common.exception;

import javax.security.sasl.SaslException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/shaded/com/ongres/scram/common/exception/ScramException.class */
public class ScramException extends SaslException {
    public ScramException(String detail) {
        super(detail);
    }

    public ScramException(String detail, Throwable ex) {
        super(detail, ex);
    }
}