package org.hibernate.annotations.common.reflection.java.generics;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-commons-annotations-6.0.2.Final.jar:org/hibernate/annotations/common/reflection/java/generics/ParameterizedTypeImpl.class */
public class ParameterizedTypeImpl implements ParameterizedType {
    private final Type[] substTypeArgs;
    private final Type rawType;
    private final Type ownerType;

    public ParameterizedTypeImpl(Type rawType, Type[] substTypeArgs, Type ownerType) {
        this.substTypeArgs = substTypeArgs;
        this.rawType = rawType;
        this.ownerType = ownerType;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type[] getActualTypeArguments() {
        return this.substTypeArgs;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type getRawType() {
        return this.rawType;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type getOwnerType() {
        return this.ownerType;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ParameterizedType)) {
            return false;
        }
        ParameterizedType other = (ParameterizedType) obj;
        return Objects.equals(getOwnerType(), other.getOwnerType()) && Objects.equals(getRawType(), other.getRawType()) && Arrays.equals(getActualTypeArguments(), other.getActualTypeArguments());
    }

    public int hashCode() {
        return (Arrays.hashCode(getActualTypeArguments()) ^ Objects.hashCode(getOwnerType())) ^ Objects.hashCode(getRawType());
    }

    public String toString() {
        Type[] typeArr;
        StringBuilder sb = new StringBuilder();
        if (this.ownerType != null) {
            sb.append(this.ownerType.getTypeName());
            sb.append(PropertiesBeanDefinitionReader.CONSTRUCTOR_ARG_PREFIX);
            if (this.ownerType instanceof ParameterizedType) {
                sb.append(this.rawType.getTypeName().replace(((ParameterizedType) this.ownerType).getRawType().getTypeName() + "$", ""));
            } else if (this.rawType instanceof Class) {
                sb.append(((Class) this.rawType).getSimpleName());
            } else {
                sb.append(this.rawType.getTypeName());
            }
        } else {
            sb.append(this.rawType.getTypeName());
        }
        if (this.substTypeArgs != null) {
            StringJoiner sj = new StringJoiner(", ", "<", ">");
            sj.setEmptyValue("");
            for (Type t : this.substTypeArgs) {
                sj.add(t.getTypeName());
            }
            sb.append(sj);
        }
        return sb.toString();
    }
}