package lombok.installer;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:lombok/installer/InstallException.SCL.lombok */
public class InstallException extends Exception {
    private boolean warning;

    public InstallException(String message, Throwable cause) {
        super(message, cause);
    }

    public InstallException(boolean warning, String message, Throwable cause) {
        super(message, cause);
        this.warning = warning;
    }

    public boolean isWarning() {
        return this.warning;
    }
}