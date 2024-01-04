package jakarta.persistence;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.persistence-api-3.1.0.jar:jakarta/persistence/Cache.class */
public interface Cache {
    boolean contains(Class cls, Object obj);

    void evict(Class cls, Object obj);

    void evict(Class cls);

    void evictAll();

    <T> T unwrap(Class<T> cls);
}