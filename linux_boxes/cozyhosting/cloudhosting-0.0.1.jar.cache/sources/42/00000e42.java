package lombok.installer;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:lombok/installer/UninstallException.SCL.lombok */
public class UninstallException extends Exception {
    private boolean warning;

    public UninstallException(String message, Throwable cause) {
        super(message, cause);
    }

    public UninstallException(boolean warning, String message, Throwable cause) {
        super(message, cause);
        this.warning = warning;
    }

    public boolean isWarning() {
        return this.warning;
    }
}