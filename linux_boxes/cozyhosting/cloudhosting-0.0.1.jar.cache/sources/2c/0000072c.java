package com.sun.xml.txw2;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/txw2-4.0.1.jar:com/sun/xml/txw2/TxwException.class */
public class TxwException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public TxwException(String message) {
        super(message);
    }

    public TxwException(Throwable cause) {
        super(cause);
    }

    public TxwException(String message, Throwable cause) {
        super(message, cause);
    }
}