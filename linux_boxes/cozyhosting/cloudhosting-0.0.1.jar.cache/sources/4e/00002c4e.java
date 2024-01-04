package org.hibernate.boot.jaxb.hbm.spi;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "loader-type", namespace = "http://www.hibernate.org/xsd/orm/hbm")
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/JaxbHbmLoaderType.class */
public class JaxbHbmLoaderType implements Serializable {
    @XmlAttribute(name = "query-ref", required = true)
    protected String queryRef;

    public String getQueryRef() {
        return this.queryRef;
    }

    public void setQueryRef(String value) {
        this.queryRef = value;
    }
}