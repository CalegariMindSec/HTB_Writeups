package org.glassfish.jaxb.core.v2.model.nav;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jaxb-core-4.0.1.jar:org/glassfish/jaxb/core/v2/model/nav/WildcardTypeImpl.class */
final class WildcardTypeImpl implements WildcardType {
    private final Type[] ub;
    private final Type[] lb;

    public WildcardTypeImpl(Type[] ub, Type[] lb) {
        this.ub = ub;
        this.lb = lb;
    }

    @Override // java.lang.reflect.WildcardType
    public Type[] getUpperBounds() {
        return this.ub;
    }

    @Override // java.lang.reflect.WildcardType
    public Type[] getLowerBounds() {
        return this.lb;
    }

    public int hashCode() {
        return Arrays.hashCode(this.lb) ^ Arrays.hashCode(this.ub);
    }

    public boolean equals(Object obj) {
        if (obj instanceof WildcardType) {
            WildcardType that = (WildcardType) obj;
            return Arrays.equals(that.getLowerBounds(), this.lb) && Arrays.equals(that.getUpperBounds(), this.ub);
        }
        return false;
    }
}