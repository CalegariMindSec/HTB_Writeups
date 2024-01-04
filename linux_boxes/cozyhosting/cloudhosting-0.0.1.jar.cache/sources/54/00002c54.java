package org.hibernate.boot.jaxb.hbm.spi;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MapKeyManyToManyType", namespace = "http://www.hibernate.org/xsd/orm/hbm", propOrder = {"columnOrFormula"})
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/JaxbHbmMapKeyManyToManyType.class */
public class JaxbHbmMapKeyManyToManyType implements Serializable {
    @XmlElements({@XmlElement(name = "column", namespace = "http://www.hibernate.org/xsd/orm/hbm", type = JaxbHbmColumnType.class), @XmlElement(name = "formula", namespace = "http://www.hibernate.org/xsd/orm/hbm", type = String.class)})
    protected List<Serializable> columnOrFormula;
    @XmlAttribute(name = "class")
    protected String clazz;
    @XmlAttribute(name = "column")
    protected String columnAttribute;
    @XmlAttribute(name = "entity-name")
    protected String entityName;
    @XmlAttribute(name = "foreign-key")
    protected String foreignKey;
    @XmlAttribute(name = "formula")
    protected String formulaAttribute;

    public List<Serializable> getColumnOrFormula() {
        if (this.columnOrFormula == null) {
            this.columnOrFormula = new ArrayList();
        }
        return this.columnOrFormula;
    }

    public String getClazz() {
        return this.clazz;
    }

    public void setClazz(String value) {
        this.clazz = value;
    }

    public String getColumnAttribute() {
        return this.columnAttribute;
    }

    public void setColumnAttribute(String value) {
        this.columnAttribute = value;
    }

    public String getEntityName() {
        return this.entityName;
    }

    public void setEntityName(String value) {
        this.entityName = value;
    }

    public String getForeignKey() {
        return this.foreignKey;
    }

    public void setForeignKey(String value) {
        this.foreignKey = value;
    }

    public String getFormulaAttribute() {
        return this.formulaAttribute;
    }

    public void setFormulaAttribute(String value) {
        this.formulaAttribute = value;
    }
}