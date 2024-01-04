package jakarta.persistence;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.persistence-api-3.1.0.jar:jakarta/persistence/AttributeConverter.class */
public interface AttributeConverter<X, Y> {
    Y convertToDatabaseColumn(X x);

    X convertToEntityAttribute(Y y);
}