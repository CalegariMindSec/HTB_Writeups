package org.hibernate.boot.jaxb.hbm.spi;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "natural-id-type", namespace = "http://www.hibernate.org/xsd/orm/hbm", propOrder = {"attributes"})
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/JaxbHbmNaturalIdType.class */
public class JaxbHbmNaturalIdType implements Serializable {
    @XmlElements({@XmlElement(name = "property", namespace = "http://www.hibernate.org/xsd/orm/hbm", type = JaxbHbmBasicAttributeType.class), @XmlElement(name = "many-to-one", namespace = "http://www.hibernate.org/xsd/orm/hbm", type = JaxbHbmManyToOneType.class), @XmlElement(name = "component", namespace = "http://www.hibernate.org/xsd/orm/hbm", type = JaxbHbmCompositeAttributeType.class), @XmlElement(name = "dynamic-component", namespace = "http://www.hibernate.org/xsd/orm/hbm", type = JaxbHbmDynamicComponentType.class), @XmlElement(name = "any", namespace = "http://www.hibernate.org/xsd/orm/hbm", type = JaxbHbmAnyAssociationType.class)})
    protected List<Serializable> attributes;
    @XmlAttribute(name = "mutable")
    protected Boolean mutable;

    public List<Serializable> getAttributes() {
        if (this.attributes == null) {
            this.attributes = new ArrayList();
        }
        return this.attributes;
    }

    public boolean isMutable() {
        if (this.mutable == null) {
            return false;
        }
        return this.mutable.booleanValue();
    }

    public void setMutable(Boolean value) {
        this.mutable = value;
    }
}