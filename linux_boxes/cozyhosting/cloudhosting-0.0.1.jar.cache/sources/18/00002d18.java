package org.hibernate.boot.jaxb.mapping;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "map-key-class", namespace = "http://www.hibernate.org/xsd/orm/mapping")
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/mapping/JaxbMapKeyClass.class */
public class JaxbMapKeyClass implements Serializable {
    @XmlAttribute(name = "class", required = true)
    protected String clazz;

    public String getClazz() {
        return this.clazz;
    }

    public void setClazz(String value) {
        this.clazz = value;
    }
}