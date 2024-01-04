package org.hibernate.annotations.common.reflection.java.generics;

import java.lang.reflect.Type;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-commons-annotations-6.0.2.Final.jar:org/hibernate/annotations/common/reflection/java/generics/TypeEnvironment.class */
public interface TypeEnvironment {
    Type bind(Type type);
}