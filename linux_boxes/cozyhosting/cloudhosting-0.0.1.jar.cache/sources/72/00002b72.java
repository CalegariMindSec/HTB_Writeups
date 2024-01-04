package org.hibernate.annotations.common.reflection.java.generics;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Objects;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-commons-annotations-6.0.2.Final.jar:org/hibernate/annotations/common/reflection/java/generics/GenericArrayTypeImpl.class */
public class GenericArrayTypeImpl implements GenericArrayType {
    private final Type componentType;

    public GenericArrayTypeImpl(Type componentType) {
        this.componentType = componentType;
    }

    @Override // java.lang.reflect.GenericArrayType
    public Type getGenericComponentType() {
        return this.componentType;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GenericArrayType)) {
            return false;
        }
        GenericArrayType other = (GenericArrayType) obj;
        return Objects.equals(getGenericComponentType(), other.getGenericComponentType());
    }

    public int hashCode() {
        return Objects.hashCode(getGenericComponentType());
    }
}