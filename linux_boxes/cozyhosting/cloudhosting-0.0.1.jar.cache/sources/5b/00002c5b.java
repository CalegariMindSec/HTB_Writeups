package org.hibernate.boot.jaxb.hbm.spi;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NativeQueryPropertyReturnType", namespace = "http://www.hibernate.org/xsd/orm/hbm", propOrder = {"returnColumn"})
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/JaxbHbmNativeQueryPropertyReturnType.class */
public class JaxbHbmNativeQueryPropertyReturnType implements Serializable {
    @XmlElement(name = "return-column", namespace = "http://www.hibernate.org/xsd/orm/hbm")
    protected List<JaxbHbmReturnColumn> returnColumn;
    @XmlAttribute(name = "column")
    protected String column;
    @XmlAttribute(name = "name", required = true)
    protected String name;

    public List<JaxbHbmReturnColumn> getReturnColumn() {
        if (this.returnColumn == null) {
            this.returnColumn = new ArrayList();
        }
        return this.returnColumn;
    }

    public String getColumn() {
        return this.column;
    }

    public void setColumn(String value) {
        this.column = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/JaxbHbmNativeQueryPropertyReturnType$JaxbHbmReturnColumn.class */
    public static class JaxbHbmReturnColumn implements Serializable {
        @XmlAttribute(name = "name", required = true)
        protected String name;

        public String getName() {
            return this.name;
        }

        public void setName(String value) {
            this.name = value;
        }
    }
}