package jakarta.persistence;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.persistence-api-3.1.0.jar:jakarta/persistence/TupleElement.class */
public interface TupleElement<X> {
    Class<? extends X> getJavaType();

    String getAlias();
}