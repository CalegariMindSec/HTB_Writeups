package org.hibernate.boot.jaxb.cfg.spi;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import org.hibernate.boot.cfgxml.internal.JaxbCfgProcessor;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfigPropertyType", namespace = JaxbCfgProcessor.HIBERNATE_CONFIGURATION_URI, propOrder = {"value"})
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/cfg/spi/JaxbCfgConfigPropertyType.class */
public class JaxbCfgConfigPropertyType {
    @XmlValue
    protected String value;
    @XmlAttribute(name = "name", required = true)
    protected String name;

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
}