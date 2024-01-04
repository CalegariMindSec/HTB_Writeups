package org.hibernate.metamodel.model.domain.internal;

import java.lang.reflect.Member;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/metamodel/model/domain/internal/MapMember.class */
public class MapMember implements Member {
    private String name;
    private final Class<?> type;

    public MapMember(String name, Class<?> type) {
        this.name = name;
        this.type = type;
    }

    public Class<?> getType() {
        return this.type;
    }

    @Override // java.lang.reflect.Member
    public int getModifiers() {
        return 1;
    }

    @Override // java.lang.reflect.Member
    public boolean isSynthetic() {
        return false;
    }

    @Override // java.lang.reflect.Member
    public String getName() {
        return this.name;
    }

    @Override // java.lang.reflect.Member
    public Class<?> getDeclaringClass() {
        return null;
    }
}