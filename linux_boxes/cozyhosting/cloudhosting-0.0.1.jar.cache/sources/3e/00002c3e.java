package org.hibernate.boot.jaxb.hbm.spi;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FilterParameterType", namespace = "http://www.hibernate.org/xsd/orm/hbm")
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/JaxbHbmFilterParameterType.class */
public class JaxbHbmFilterParameterType implements Serializable {
    @XmlAttribute(name = "name", required = true)
    protected String parameterName;
    @XmlAttribute(name = "type", required = true)
    protected String parameterValueTypeName;

    public String getParameterName() {
        return this.parameterName;
    }

    public void setParameterName(String value) {
        this.parameterName = value;
    }

    public String getParameterValueTypeName() {
        return this.parameterValueTypeName;
    }

    public void setParameterValueTypeName(String value) {
        this.parameterValueTypeName = value;
    }
}