package jakarta.xml.bind;

import java.io.Serializable;
import javax.xml.namespace.QName;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.xml.bind-api-4.0.0.jar:jakarta/xml/bind/JAXBElement.class */
public class JAXBElement<T> implements Serializable {
    protected final QName name;
    protected final Class<T> declaredType;
    protected final Class<?> scope;
    protected T value;
    protected boolean nil;
    private static final long serialVersionUID = 1;

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.xml.bind-api-4.0.0.jar:jakarta/xml/bind/JAXBElement$GlobalScope.class */
    public static final class GlobalScope {
        private GlobalScope() {
        }
    }

    /* JADX WARN: Incorrect type for immutable var: ssa=java.lang.Class<?>, code=java.lang.Class, for r6v0, types: [java.lang.Class<?>] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public JAXBElement(javax.xml.namespace.QName r4, java.lang.Class<T> r5, java.lang.Class r6, T r7) {
        /*
            r3 = this;
            r0 = r3
            r0.<init>()
            r0 = r3
            r1 = 0
            r0.nil = r1
            r0 = r5
            if (r0 == 0) goto L11
            r0 = r4
            if (r0 != 0) goto L19
        L11:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r1 = r0
            r1.<init>()
            throw r0
        L19:
            r0 = r3
            r1 = r5
            r0.declaredType = r1
            r0 = r6
            if (r0 != 0) goto L25
            java.lang.Class<jakarta.xml.bind.JAXBElement$GlobalScope> r0 = jakarta.xml.bind.JAXBElement.GlobalScope.class
            r6 = r0
        L25:
            r0 = r3
            r1 = r6
            r0.scope = r1
            r0 = r3
            r1 = r4
            r0.name = r1
            r0 = r3
            r1 = r7
            r0.setValue(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jakarta.xml.bind.JAXBElement.<init>(javax.xml.namespace.QName, java.lang.Class, java.lang.Class, java.lang.Object):void");
    }

    public JAXBElement(QName name, Class<T> declaredType, T value) {
        this(name, declaredType, GlobalScope.class, value);
    }

    public Class<T> getDeclaredType() {
        return this.declaredType;
    }

    public QName getName() {
        return this.name;
    }

    public void setValue(T t) {
        this.value = t;
    }

    public T getValue() {
        return this.value;
    }

    public Class<?> getScope() {
        return this.scope;
    }

    public boolean isNil() {
        return this.value == null || this.nil;
    }

    public void setNil(boolean value) {
        this.nil = value;
    }

    public boolean isGlobalScope() {
        return this.scope == GlobalScope.class;
    }

    public boolean isTypeSubstituted() {
        return (this.value == null || this.value.getClass() == this.declaredType) ? false : true;
    }
}