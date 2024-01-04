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
@XmlType(name = "properties-type", namespace = "http://www.hibernate.org/xsd/orm/hbm", propOrder = {"attributes"})
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/JaxbHbmPropertiesType.class */
public class JaxbHbmPropertiesType implements Serializable {
    @XmlElements({@XmlElement(name = "property", namespace = "http://www.hibernate.org/xsd/orm/hbm", type = JaxbHbmBasicAttributeType.class), @XmlElement(name = "many-to-one", namespace = "http://www.hibernate.org/xsd/orm/hbm", type = JaxbHbmManyToOneType.class), @XmlElement(name = "component", namespace = "http://www.hibernate.org/xsd/orm/hbm", type = JaxbHbmCompositeAttributeType.class), @XmlElement(name = "dynamic-component", namespace = "http://www.hibernate.org/xsd/orm/hbm", type = JaxbHbmDynamicComponentType.class)})
    protected List<Serializable> attributes;
    @XmlAttribute(name = "insert")
    protected Boolean insert;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "node")
    protected String node;
    @XmlAttribute(name = "optimistic-lock")
    protected Boolean optimisticLock;
    @XmlAttribute(name = "unique")
    protected Boolean unique;
    @XmlAttribute(name = "update")
    protected Boolean update;

    public List<Serializable> getAttributes() {
        if (this.attributes == null) {
            this.attributes = new ArrayList();
        }
        return this.attributes;
    }

    public boolean isInsert() {
        if (this.insert == null) {
            return true;
        }
        return this.insert.booleanValue();
    }

    public void setInsert(Boolean value) {
        this.insert = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getNode() {
        return this.node;
    }

    public void setNode(String value) {
        this.node = value;
    }

    public boolean isOptimisticLock() {
        if (this.optimisticLock == null) {
            return true;
        }
        return this.optimisticLock.booleanValue();
    }

    public void setOptimisticLock(Boolean value) {
        this.optimisticLock = value;
    }

    public boolean isUnique() {
        if (this.unique == null) {
            return false;
        }
        return this.unique.booleanValue();
    }

    public void setUnique(Boolean value) {
        this.unique = value;
    }

    public boolean isUpdate() {
        if (this.update == null) {
            return true;
        }
        return this.update.booleanValue();
    }

    public void setUpdate(Boolean value) {
        this.update = value;
    }
}