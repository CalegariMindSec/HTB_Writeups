package jakarta.xml.bind.annotation.adapters;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.xml.bind-api-4.0.0.jar:jakarta/xml/bind/annotation/adapters/XmlAdapter.class */
public abstract class XmlAdapter<ValueType, BoundType> {
    public abstract BoundType unmarshal(ValueType valuetype) throws Exception;

    public abstract ValueType marshal(BoundType boundtype) throws Exception;
}