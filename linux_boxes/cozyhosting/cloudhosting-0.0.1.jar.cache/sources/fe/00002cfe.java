package org.hibernate.boot.jaxb.mapping;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fetch-profile", namespace = "http://www.hibernate.org/xsd/orm/mapping", propOrder = {"fetch"})
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/mapping/JaxbFetchProfile.class */
public class JaxbFetchProfile implements Serializable {
    @XmlElement(namespace = "http://www.hibernate.org/xsd/orm/mapping")
    protected List<JaxbFetch> fetch;
    @XmlAttribute(name = "name", required = true)
    protected String name;

    public List<JaxbFetch> getFetch() {
        if (this.fetch == null) {
            this.fetch = new ArrayList();
        }
        return this.fetch;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/mapping/JaxbFetchProfile$JaxbFetch.class */
    public static class JaxbFetch implements Serializable {
        @XmlAttribute(name = "association", required = true)
        protected String association;
        @XmlAttribute(name = "entity")
        protected String entity;
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlAttribute(name = "style")
        protected String style;

        public String getAssociation() {
            return this.association;
        }

        public void setAssociation(String value) {
            this.association = value;
        }

        public String getEntity() {
            return this.entity;
        }

        public void setEntity(String value) {
            this.entity = value;
        }

        public String getStyle() {
            return this.style;
        }

        public void setStyle(String value) {
            this.style = value;
        }
    }
}