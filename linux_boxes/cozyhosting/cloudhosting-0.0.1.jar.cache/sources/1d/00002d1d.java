package org.hibernate.boot.jaxb.mapping;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "named-attribute-node", namespace = "http://www.hibernate.org/xsd/orm/mapping")
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/mapping/JaxbNamedAttributeNode.class */
public class JaxbNamedAttributeNode implements Serializable {
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "subgraph")
    protected String subgraph;
    @XmlAttribute(name = "key-subgraph")
    protected String keySubgraph;

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getSubgraph() {
        return this.subgraph;
    }

    public void setSubgraph(String value) {
        this.subgraph = value;
    }

    public String getKeySubgraph() {
        return this.keySubgraph;
    }

    public void setKeySubgraph(String value) {
        this.keySubgraph = value;
    }
}