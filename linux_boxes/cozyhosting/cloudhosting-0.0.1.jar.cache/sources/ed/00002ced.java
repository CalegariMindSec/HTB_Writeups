package org.hibernate.boot.jaxb.mapping;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "custom-loader", namespace = "http://www.hibernate.org/xsd/orm/mapping")
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/mapping/JaxbCustomLoader.class */
public class JaxbCustomLoader implements Serializable {
    @XmlAttribute(name = "query-ref", required = true)
    protected String queryRef;

    public String getQueryRef() {
        return this.queryRef;
    }

    public void setQueryRef(String value) {
        this.queryRef = value;
    }
}