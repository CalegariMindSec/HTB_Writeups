package org.hibernate.internal.util;

import jakarta.persistence.AttributeConverter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/GenericsHelper.class */
public class GenericsHelper {
    public static ParameterizedType extractParameterizedType(Type base) {
        Class<?> clazz;
        if (base == null || (clazz = extractClass(base)) == null) {
            return null;
        }
        List<Type> types = new ArrayList<>();
        types.add(clazz.getGenericSuperclass());
        types.addAll(Arrays.asList(clazz.getGenericInterfaces()));
        for (Type type : types) {
            Type type2 = resolveType(type, base);
            if (type2 instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type2;
                if (AttributeConverter.class.equals(parameterizedType.getRawType())) {
                    return parameterizedType;
                }
            }
            ParameterizedType parameterizedType2 = extractParameterizedType(type2);
            if (parameterizedType2 != null) {
                return parameterizedType2;
            }
        }
        return null;
    }

    private static Type resolveTypeVariable(TypeVariable<?> typeVariable, ParameterizedType context) {
        Class<?> clazz = extractClass(context.getRawType());
        if (clazz == null) {
            return null;
        }
        TypeVariable<?>[] typeParameters = clazz.getTypeParameters();
        for (int idx = 0; idx < typeParameters.length; idx++) {
            if (typeVariable.getName().equals(typeParameters[idx].getName())) {
                return resolveType(context.getActualTypeArguments()[idx], context);
            }
        }
        return typeVariable;
    }

    public static Class<?> extractClass(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return extractClass(((ParameterizedType) type).getRawType());
        }
        return null;
    }

    private static Type resolveType(Type target, Type context) {
        if (target instanceof ParameterizedType) {
            return resolveParameterizedType((ParameterizedType) target, context);
        }
        if (target instanceof TypeVariable) {
            return resolveTypeVariable((TypeVariable) target, (ParameterizedType) context);
        }
        return target;
    }

    private static ParameterizedType resolveParameterizedType(final ParameterizedType parameterizedType, Type context) {
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        final Type[] resolvedTypeArguments = new Type[actualTypeArguments.length];
        for (int idx = 0; idx < actualTypeArguments.length; idx++) {
            resolvedTypeArguments[idx] = resolveType(actualTypeArguments[idx], context);
        }
        return new ParameterizedType() { // from class: org.hibernate.internal.util.GenericsHelper.1
            @Override // java.lang.reflect.ParameterizedType
            public Type[] getActualTypeArguments() {
                return resolvedTypeArguments;
            }

            @Override // java.lang.reflect.ParameterizedType
            public Type getRawType() {
                return parameterizedType.getRawType();
            }

            @Override // java.lang.reflect.ParameterizedType
            public Type getOwnerType() {
                return parameterizedType.getOwnerType();
            }
        };
    }
}