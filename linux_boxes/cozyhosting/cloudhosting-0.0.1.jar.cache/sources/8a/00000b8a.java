package jakarta.transaction;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.transaction-api-2.0.1.jar:jakarta/transaction/SystemException.class */
public class SystemException extends Exception {
    private static final long serialVersionUID = 839699079412719325L;
    public int errorCode;

    public SystemException() {
    }

    public SystemException(String s) {
        super(s);
    }

    public SystemException(int errcode) {
        this.errorCode = errcode;
    }
}