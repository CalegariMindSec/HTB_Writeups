package jakarta.persistence.metamodel;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.persistence-api-3.1.0.jar:jakarta/persistence/metamodel/Bindable.class */
public interface Bindable<T> {

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.persistence-api-3.1.0.jar:jakarta/persistence/metamodel/Bindable$BindableType.class */
    public enum BindableType {
        SINGULAR_ATTRIBUTE,
        PLURAL_ATTRIBUTE,
        ENTITY_TYPE
    }

    BindableType getBindableType();

    Class<T> getBindableJavaType();
}