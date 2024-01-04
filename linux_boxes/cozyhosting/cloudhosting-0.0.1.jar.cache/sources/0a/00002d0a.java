package org.hibernate.boot.jaxb.mapping;

import ch.qos.logback.core.joran.conditional.IfAction;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlMixed;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hbm-filter", namespace = "http://www.hibernate.org/xsd/orm/mapping", propOrder = {"content"})
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/mapping/JaxbHbmFilter.class */
public class JaxbHbmFilter implements Serializable {
    @XmlMixed
    @XmlElementRefs({@XmlElementRef(name = "aliases", namespace = "http://www.hibernate.org/xsd/orm/mapping", type = JAXBElement.class), @XmlElementRef(name = IfAction.CONDITION_ATTRIBUTE, namespace = "http://www.hibernate.org/xsd/orm/mapping", type = JAXBElement.class)})
    protected List<Serializable> content;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "autoAliasInjection")
    protected Boolean autoAliasInjection;
    @XmlAttribute(name = IfAction.CONDITION_ATTRIBUTE)
    protected String condition;

    public List<Serializable> getContent() {
        if (this.content == null) {
            this.content = new ArrayList();
        }
        return this.content;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public Boolean isAutoAliasInjection() {
        return this.autoAliasInjection;
    }

    public void setAutoAliasInjection(Boolean value) {
        this.autoAliasInjection = value;
    }

    public String getCondition() {
        return this.condition;
    }

    public void setCondition(String value) {
        this.condition = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {"value"})
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/mapping/JaxbHbmFilter$JaxbAliases.class */
    public static class JaxbAliases implements Serializable {
        @XmlValue
        protected String value;
        @XmlAttribute(name = "alias", required = true)
        protected String alias;
        @XmlAttribute(name = "table")
        protected String table;
        @XmlAttribute(name = "entity")
        protected String entity;

        public String getValue() {
            return this.value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getAlias() {
            return this.alias;
        }

        public void setAlias(String value) {
            this.alias = value;
        }

        public String getTable() {
            return this.table;
        }

        public void setTable(String value) {
            this.table = value;
        }

        public String getEntity() {
            return this.entity;
        }

        public void setEntity(String value) {
            this.entity = value;
        }
    }
}