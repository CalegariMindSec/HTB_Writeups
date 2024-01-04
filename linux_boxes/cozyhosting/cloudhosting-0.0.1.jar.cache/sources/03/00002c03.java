package org.hibernate.boot.jaxb.cfg.spi;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import org.hibernate.boot.cfgxml.internal.JaxbCfgProcessor;
import org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader;
import org.springframework.util.ResourceUtils;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MappingReferenceType", namespace = JaxbCfgProcessor.HIBERNATE_CONFIGURATION_URI)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/cfg/spi/JaxbCfgMappingReferenceType.class */
public class JaxbCfgMappingReferenceType {
    @XmlAttribute(name = "class")
    protected String clazz;
    @XmlAttribute(name = "file")
    protected String file;
    @XmlAttribute(name = ResourceUtils.URL_PROTOCOL_JAR)
    protected String jar;
    @XmlAttribute(name = "package")
    protected String _package;
    @XmlAttribute(name = DefaultBeanDefinitionDocumentReader.RESOURCE_ATTRIBUTE)
    protected String resource;

    public String getClazz() {
        return this.clazz;
    }

    public void setClazz(String value) {
        this.clazz = value;
    }

    public String getFile() {
        return this.file;
    }

    public void setFile(String value) {
        this.file = value;
    }

    public String getJar() {
        return this.jar;
    }

    public void setJar(String value) {
        this.jar = value;
    }

    public String getPackage() {
        return this._package;
    }

    public void setPackage(String value) {
        this._package = value;
    }

    public String getResource() {
        return this.resource;
    }

    public void setResource(String value) {
        this.resource = value;
    }
}