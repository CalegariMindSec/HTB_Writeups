package lombok.core;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:lombok/core/DiagnosticsReceiver.SCL.lombok */
public interface DiagnosticsReceiver {
    public static final DiagnosticsReceiver CONSOLE = new DiagnosticsReceiver() { // from class: lombok.core.DiagnosticsReceiver.1
        @Override // lombok.core.DiagnosticsReceiver
        public void addError(String message) {
            System.err.println("Error: " + message);
        }

        @Override // lombok.core.DiagnosticsReceiver
        public void addWarning(String message) {
            System.out.println("Warning: " + message);
        }
    };

    void addError(String str);

    void addWarning(String str);
}