package jakarta.xml.bind;

import java.security.PrivilegedAction;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.xml.bind-api-4.0.0.jar:jakarta/xml/bind/GetPropertyAction.class */
final class GetPropertyAction implements PrivilegedAction<String> {
    private final String propertyName;

    public GetPropertyAction(String propertyName) {
        this.propertyName = propertyName;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.security.PrivilegedAction
    public String run() {
        return System.getProperty(this.propertyName);
    }
}