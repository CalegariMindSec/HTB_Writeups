package org.hibernate.boot.jaxb.hbm.spi;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "synchronize-type", namespace = "http://www.hibernate.org/xsd/orm/hbm")
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/JaxbHbmSynchronizeType.class */
public class JaxbHbmSynchronizeType implements Serializable {
    @XmlAttribute(name = "table", required = true)
    protected String table;

    public String getTable() {
        return this.table;
    }

    public void setTable(String value) {
        this.table = value;
    }
}