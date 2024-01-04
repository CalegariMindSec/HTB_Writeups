package org.hibernate.boot.jaxb.hbm.spi;

import ch.qos.logback.core.joran.conditional.IfAction;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlMixed;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FilterDefinitionType", namespace = "http://www.hibernate.org/xsd/orm/hbm", propOrder = {"content"})
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/JaxbHbmFilterDefinitionType.class */
public class JaxbHbmFilterDefinitionType implements Serializable {
    @XmlElementRef(name = "filter-param", namespace = "http://www.hibernate.org/xsd/orm/hbm", type = JAXBElement.class, required = false)
    @XmlMixed
    protected List<Serializable> content;
    @XmlAttribute(name = IfAction.CONDITION_ATTRIBUTE)
    protected String condition;
    @XmlAttribute(name = "name", required = true)
    protected String name;

    public List<Serializable> getContent() {
        if (this.content == null) {
            this.content = new ArrayList();
        }
        return this.content;
    }

    public String getCondition() {
        return this.condition;
    }

    public void setCondition(String value) {
        this.condition = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }
}