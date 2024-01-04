package org.hibernate.annotations;

import org.hibernate.usertype.CompositeUserType;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/annotations/MapKeyCompositeType.class */
public @interface MapKeyCompositeType {
    Class<? extends CompositeUserType<?>> value();
}