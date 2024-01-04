package org.hibernate.boot.jaxb.hbm.spi;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClassRenameType", namespace = "http://www.hibernate.org/xsd/orm/hbm")
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/JaxbHbmClassRenameType.class */
public class JaxbHbmClassRenameType implements Serializable {
    @XmlAttribute(name = "class", required = true)
    protected String clazz;
    @XmlAttribute(name = "rename")
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