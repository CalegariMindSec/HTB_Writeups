package org.hibernate.boot.jaxb.mapping;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "convert", namespace = "http://www.hibernate.org/xsd/orm/mapping", propOrder = {"description"})
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/mapping/JaxbConvert.class */
public class JaxbConvert implements Serializable {
    @XmlElement(namespace = "http://www.hibernate.org/xsd/orm/mapping")
    protected String description;
    @XmlAttribute(name = "converter")
    protected String converter;
    @XmlAttribute(name = "attribute-name")
    protected String attributeName;
    @XmlAttribute(name = "disable-conversion")
    protected Boolean disableConversion;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getConverter() {
        return this.converter;
    }

    public void setConverter(String value) {
        this.converter = value;
    }

    public String getAttributeName() {
        return this.attributeName;
    }

    public void setAttributeName(String value) {
        this.attributeName = value;
    }

    public Boolean isDisableConversion() {
        return this.disableConversion;
    }

    public void setDisableConversion(Boolean value) {
        this.disableConversion = value;
    }
}