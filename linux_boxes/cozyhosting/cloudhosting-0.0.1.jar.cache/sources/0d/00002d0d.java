package org.hibernate.boot.jaxb.mapping;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hql-import", namespace = "http://www.hibernate.org/xsd/orm/mapping")
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/mapping/JaxbHqlImport.class */
public class JaxbHqlImport implements Serializable {
    @XmlAttribute(name = "class", required = true)
    protected String clazz;
    @XmlAttribute(name = "rename", required = true)
    protected String rename;

    public String getClazz() {
        return this.clazz;
    }

    public void setClazz(String value) {
        this.clazz = value;
    }

    public String getRename() {
        return this.rename;
    }

    public void setRename(String value) {
        this.rename = value;
    }
}