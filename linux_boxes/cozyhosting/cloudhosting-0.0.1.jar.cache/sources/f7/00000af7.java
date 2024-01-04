package jakarta.persistence.metamodel;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.persistence-api-3.1.0.jar:jakarta/persistence/metamodel/Type.class */
public interface Type<X> {

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.persistence-api-3.1.0.jar:jakarta/persistence/metamodel/Type$PersistenceType.class */
    public enum PersistenceType {
        ENTITY,
        EMBEDDABLE,
        MAPPED_SUPERCLASS,
        BASIC
    }

    PersistenceType getPersistenceType();

    Class<X> getJavaType();
}