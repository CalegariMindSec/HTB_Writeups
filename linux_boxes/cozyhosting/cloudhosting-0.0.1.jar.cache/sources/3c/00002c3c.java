package org.hibernate.boot.jaxb.hbm.spi;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filter-alias-mapping-type", namespace = "http://www.hibernate.org/xsd/orm/hbm", propOrder = {"value"})
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/JaxbHbmFilterAliasMappingType.class */
public class JaxbHbmFilterAliasMappingType implements Serializable {
    @XmlValue
    protected String value;
    @XmlAttribute(name = "alias", required = true)
    protected String alias;
    @XmlAttribute(name = "table")
    protected String table;
    @XmlAttribute(name = "entity")
    protected String entity;

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String value) {
        this.alias = value;
    }

    public String getTable() {
        return this.table;
    }

    public void setTable(String value) {
        this.table = value;
    }

    public String getEntity() {
        return this.entity;
    }

    public void setEntity(String value) {
        this.entity = value;
    }
}