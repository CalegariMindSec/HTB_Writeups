package org.hibernate.boot.jaxb.mapping;

import ch.qos.logback.core.joran.conditional.IfAction;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filter-def", namespace = "http://www.hibernate.org/xsd/orm/mapping", propOrder = {"filterParam", IfAction.CONDITION_ATTRIBUTE})
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/mapping/JaxbFilterDef.class */
public class JaxbFilterDef implements Serializable {
    @XmlElement(name = "filter-param", namespace = "http://www.hibernate.org/xsd/orm/mapping")
    protected List<JaxbFilterParam> filterParam;
    @XmlElement(namespace = "http://www.hibernate.org/xsd/orm/mapping")
    protected String condition;
    @XmlAttribute(name = "name", required = true)
    protected String name;

    public List<JaxbFilterParam> getFilterParam() {
        if (this.filterParam == null) {
            this.filterParam = new ArrayList();
        }
        return this.filterParam;
    }

    public String getCondition() {
        return this.condition;
    }

    public void setCondition(String value) {
        this.condition = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/mapping/JaxbFilterDef$JaxbFilterParam.class */
    public static class JaxbFilterParam implements Serializable {
        @XmlAttribute(name = "name", required = true)
        protected String name;
        @XmlAttribute(name = "type", required = true)
        protected String type;

        public String getName() {
            return this.name;
        }

        public void setName(String value) {
            this.name = value;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String value) {
            this.type = value;
        }
    }
}