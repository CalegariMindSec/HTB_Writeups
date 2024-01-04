package org.hibernate.boot.jaxb.mapping;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "unique-constraint", namespace = "http://www.hibernate.org/xsd/orm/mapping", propOrder = {"columnName"})
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/mapping/JaxbUniqueConstraint.class */
public class JaxbUniqueConstraint implements Serializable {
    @XmlElement(name = "column-name", namespace = "http://www.hibernate.org/xsd/orm/mapping", required = true)
    protected List<String> columnName;
    @XmlAttribute(name = "name")
    protected String name;

    public List<String> getColumnName() {
        if (this.columnName == null) {
            this.columnName = new ArrayList();
        }
        return this.columnName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }
}