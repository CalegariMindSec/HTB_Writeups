package org.hibernate.jpa;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/jpa/SpecHints.class */
public interface SpecHints {
    public static final String HINT_SPEC_FETCH_GRAPH = "jakarta.persistence.fetchgraph";
    public static final String HINT_SPEC_LOAD_GRAPH = "jakarta.persistence.loadgraph";
    public static final String HINT_SPEC_LOCK_TIMEOUT = "jakarta.persistence.lock.timeout";
    public static final String HINT_SPEC_LOCK_SCOPE = "jakarta.persistence.lock.scope";
    public static final String HINT_SPEC_QUERY_TIMEOUT = "jakarta.persistence.query.timeout";
    public static final String HINT_SPEC_CACHE_RETRIEVE_MODE = "jakarta.persistence.cache.retrieveMode";
    public static final String HINT_SPEC_CACHE_STORE_MODE = "jakarta.persistence.cache.storeMode";
}