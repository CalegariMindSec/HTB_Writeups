package jakarta.el;

import java.io.Serializable;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-el-10.1.5.jar:jakarta/el/ValueReference.class */
public class ValueReference implements Serializable {
    private static final long serialVersionUID = 1;
    private final Object base;
    private final Object property;

    public ValueReference(Object base, Object property) {
        this.base = base;
        this.property = property;
    }

    public Object getBase() {
        return this.base;
    }

    public Object getProperty() {
        return this.property;
    }
}