package org.hibernate.boot.jaxb.hbm.spi;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import java.io.Serializable;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ToolingHintType", namespace = "http://www.hibernate.org/xsd/orm/hbm", propOrder = {"value"})
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/JaxbHbmToolingHintType.class */
public class JaxbHbmToolingHintType implements Serializable {
    @XmlValue
    protected String value;
    @XmlAttribute(name = BeanDefinitionParserDelegate.QUALIFIER_ATTRIBUTE_ELEMENT, required = true)
    protected String name;
    @XmlAttribute(name = "inherit")
    protected Boolean inheritable;

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public boolean isInheritable() {
        if (this.inheritable == null) {
            return true;
        }
        return this.inheritable.booleanValue();
    }

    public void setInheritable(Boolean value) {
        this.inheritable = value;
    }
}