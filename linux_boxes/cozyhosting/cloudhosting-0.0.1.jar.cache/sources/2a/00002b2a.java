package org.hibernate.annotations;

@Deprecated(since = "6.0")
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/annotations/QueryHints.class */
public final class QueryHints {
    public static final String READ_ONLY = "org.hibernate.readOnly";
    public static final String CACHEABLE = "org.hibernate.cacheable";
    public static final String CACHE_MODE = "org.hibernate.cacheMode";
    public static final String CACHE_REGION = "org.hibernate.cacheRegion";
    public static final String COMMENT = "org.hibernate.comment";
    public static final String FETCH_SIZE = "org.hibernate.fetchSize";
    public static final String FLUSH_MODE = "org.hibernate.flushMode";
    public static final String TIMEOUT_HIBERNATE = "org.hibernate.timeout";
    public static final String TIMEOUT_JAKARTA_JPA = "jakarta.persistence.query.timeout";
    public static final String NATIVE_LOCKMODE = "org.hibernate.lockMode";
    public static final String FOLLOW_ON_LOCKING = "hibernate.query.followOnLocking";
    public static final String NATIVE_SPACES = "org.hibernate.query.native.spaces";
    @Deprecated
    public static final String CALLABLE_FUNCTION = "org.hibernate.callableFunction";
    public static final String TIMEOUT_JPA = "javax.persistence.query.timeout";

    private QueryHints() {
    }
}