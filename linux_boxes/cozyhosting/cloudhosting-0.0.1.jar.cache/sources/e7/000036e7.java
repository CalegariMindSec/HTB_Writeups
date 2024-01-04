package org.hibernate.jpa;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/jpa/HibernateHints.class */
public interface HibernateHints {
    public static final String HINT_FLUSH_MODE = "org.hibernate.flushMode";
    public static final String HINT_TIMEOUT = "org.hibernate.timeout";
    public static final String HINT_READ_ONLY = "org.hibernate.readOnly";
    public static final String HINT_FETCH_SIZE = "org.hibernate.fetchSize";
    public static final String HINT_CACHEABLE = "org.hibernate.cacheable";
    public static final String HINT_CACHE_REGION = "org.hibernate.cacheRegion";
    public static final String HINT_CACHE_MODE = "org.hibernate.cacheMode";
    public static final String HINT_COMMENT = "org.hibernate.comment";
    public static final String HINT_FOLLOW_ON_LOCKING = "hibernate.query.followOnLocking";
    public static final String HINT_NATIVE_LOCK_MODE = "org.hibernate.lockMode";
    public static final String HINT_NATIVE_SPACES = "org.hibernate.query.native.spaces";
    public static final String HINT_CALLABLE_FUNCTION = "org.hibernate.callableFunction";
}