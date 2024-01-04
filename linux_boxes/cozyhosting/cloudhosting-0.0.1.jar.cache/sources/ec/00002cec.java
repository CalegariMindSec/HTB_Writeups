package org.hibernate.boot.jaxb.mapping;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "converter", namespace = "http://www.hibernate.org/xsd/orm/mapping", propOrder = {"description"})
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/mapping/JaxbConverter.class */
public class JaxbConverter implements Serializable {
    @XmlElement(namespace = "http://www.hibernate.org/xsd/orm/mapping")
    protected String description;
    @XmlAttribute(name = "class", required = true)
    protected String clazz;
    @XmlAttribute(name = "auto-apply")
    protected Boolean autoApply;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getClazz() {
        return this.clazz;
    }

    public void setClazz(String value) {
        this.clazz = value;
    }

    public Boolean isAutoApply() {
        return this.autoApply;
    }

    public void setAutoApply(Boolean value) {
        this.autoApply = value;
    }
}