package org.hibernate.boot.jaxb.mapping;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "database-object-scope", namespace = "http://www.hibernate.org/xsd/orm/mapping", propOrder = {"content"})
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/mapping/JaxbDatabaseObjectScope.class */
public class JaxbDatabaseObjectScope implements Serializable {
    @XmlValue
    protected String content;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "minimumVersion")
    protected String minimumVersion;
    @XmlAttribute(name = "maximumVersion")
    protected String maximumVersion;

    public String getContent() {
        return this.content;
    }

    public void setContent(String value) {
        this.content = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getMinimumVersion() {
        return this.minimumVersion;
    }

    public void setMinimumVersion(String value) {
        this.minimumVersion = value;
    }

    public String getMaximumVersion() {
        return this.maximumVersion;
    }

    public void setMaximumVersion(String value) {
        this.maximumVersion = value;
    }
}