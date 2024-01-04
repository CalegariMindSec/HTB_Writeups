package org.hibernate.boot.jaxb.mapping;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = BeanDefinitionParserDelegate.INDEX_ATTRIBUTE, namespace = "http://www.hibernate.org/xsd/orm/mapping", propOrder = {"description"})
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/mapping/JaxbIndex.class */
public class JaxbIndex implements Serializable {
    @XmlElement(namespace = "http://www.hibernate.org/xsd/orm/mapping")
    protected String description;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "column-list", required = true)
    protected String columnList;
    @XmlAttribute(name = "unique")
    protected Boolean unique;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getColumnList() {
        return this.columnList;
    }

    public void setColumnList(String value) {
        this.columnList = value;
    }

    public Boolean isUnique() {
        return this.unique;
    }

    public void setUnique(Boolean value) {
        this.unique = value;
    }
}