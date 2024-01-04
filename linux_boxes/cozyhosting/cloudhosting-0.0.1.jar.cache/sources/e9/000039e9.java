package org.hibernate.query;

import java.util.Collection;
import org.hibernate.MappingException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/query/SynchronizeableQuery.class */
public interface SynchronizeableQuery {
    Collection<String> getSynchronizedQuerySpaces();

    SynchronizeableQuery addSynchronizedQuerySpace(String str);

    SynchronizeableQuery addSynchronizedEntityName(String str) throws MappingException;

    SynchronizeableQuery addSynchronizedEntityClass(Class<?> cls) throws MappingException;
}