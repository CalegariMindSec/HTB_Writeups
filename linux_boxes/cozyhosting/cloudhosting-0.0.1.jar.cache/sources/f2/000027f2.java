package org.glassfish.jaxb.core.v2.model.nav;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jaxb-core-4.0.1.jar:org/glassfish/jaxb/core/v2/model/nav/TypeVisitor.class */
abstract class TypeVisitor<T, P> {
    static final /* synthetic */ boolean $assertionsDisabled;

    protected abstract T onClass(Class<?> cls, P p);

    protected abstract T onParameterizdType(ParameterizedType parameterizedType, P p);

    protected abstract T onGenericArray(GenericArrayType genericArrayType, P p);

    protected abstract T onVariable(TypeVariable typeVariable, P p);

    protected abstract T onWildcard(WildcardType wildcardType, P p);

    static {
        $assertionsDisabled = !TypeVisitor.class.desiredAssertionStatus();
    }

    public final T visit(Type t, P param) {
        if ($assertionsDisabled || t != null) {
            if (t instanceof Class) {
                return onClass((Class) t, param);
            }
            if (t instanceof ParameterizedType) {
                return onParameterizdType((ParameterizedType) t, param);
            }
            if (t instanceof GenericArrayType) {
                return onGenericArray((GenericArrayType) t, param);
            }
            if (t instanceof WildcardType) {
                return onWildcard((WildcardType) t, param);
            }
            if (t instanceof TypeVariable) {
                return onVariable((TypeVariable) t, param);
            }
            if ($assertionsDisabled) {
                throw new IllegalArgumentException();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }
}