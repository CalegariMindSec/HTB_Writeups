package org.glassfish.jaxb.core.v2.model.nav;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import org.springframework.util.ClassUtils;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jaxb-core-4.0.1.jar:org/glassfish/jaxb/core/v2/model/nav/GenericArrayTypeImpl.class */
final class GenericArrayTypeImpl implements GenericArrayType {
    private Type genericComponentType;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !GenericArrayTypeImpl.class.desiredAssertionStatus();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GenericArrayTypeImpl(Type ct) {
        if (!$assertionsDisabled && ct == null) {
            throw new AssertionError();
        }
        this.genericComponentType = ct;
    }

    @Override // java.lang.reflect.GenericArrayType
    public Type getGenericComponentType() {
        return this.genericComponentType;
    }

    public String toString() {
        Type componentType = getGenericComponentType();
        StringBuilder sb = new StringBuilder();
        if (componentType instanceof Class) {
            sb.append(((Class) componentType).getName());
        } else {
            sb.append(componentType.toString());
        }
        sb.append(ClassUtils.ARRAY_SUFFIX);
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (o instanceof GenericArrayType) {
            GenericArrayType that = (GenericArrayType) o;
            Type thatComponentType = that.getGenericComponentType();
            return this.genericComponentType.equals(thatComponentType);
        }
        return false;
    }

    public int hashCode() {
        return this.genericComponentType.hashCode();
    }
}