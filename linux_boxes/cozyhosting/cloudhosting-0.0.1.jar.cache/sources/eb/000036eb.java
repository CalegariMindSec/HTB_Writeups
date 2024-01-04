package org.hibernate.jpa;

@Deprecated(since = "6.0")
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/jpa/LegacySpecHints.class */
public interface LegacySpecHints {
    public static final String HINT_JAVAEE_FETCH_GRAPH = "javax.persistence.fetchgraph";
    public static final String HINT_JAVAEE_LOAD_GRAPH = "javax.persistence.loadgraph";
    public static final String HINT_JAVAEE_LOCK_TIMEOUT = "javax.persistence.lock.timeout";
    public static final String HINT_JAVAEE_LOCK_SCOPE = "javax.persistence.lock.scope";
    public static final String HINT_JAVAEE_QUERY_TIMEOUT = "javax.persistence.query.timeout";
    public static final String HINT_JAVAEE_CACHE_RETRIEVE_MODE = "javax.persistence.cache.retrieveMode";
    public static final String HINT_JAVAEE_CACHE_STORE_MODE = "javax.persistence.cache.storeMode";
}