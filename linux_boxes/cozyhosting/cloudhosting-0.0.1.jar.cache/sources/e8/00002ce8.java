package org.hibernate.boot.jaxb.mapping;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;
import org.postgresql.jdbc.EscapedFunctions;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "column", namespace = "http://www.hibernate.org/xsd/orm/mapping", propOrder = {"comment", "check", "_default", "read", "write"})
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/mapping/JaxbColumn.class */
public class JaxbColumn implements Serializable {
    @XmlElement(namespace = "http://www.hibernate.org/xsd/orm/mapping")
    protected String comment;
    @XmlElement(namespace = "http://www.hibernate.org/xsd/orm/mapping")
    protected String check;
    @XmlElement(name = "default", namespace = "http://www.hibernate.org/xsd/orm/mapping")
    protected String _default;
    @XmlElement(namespace = "http://www.hibernate.org/xsd/orm/mapping")
    protected String read;
    @XmlElement(namespace = "http://www.hibernate.org/xsd/orm/mapping")
    protected String write;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "unique")
    protected Boolean unique;
    @XmlAttribute(name = "nullable")
    protected Boolean nullable;
    @XmlAttribute(name = "insertable")
    protected Boolean insertable;
    @XmlAttribute(name = "updatable")
    protected Boolean updatable;
    @XmlAttribute(name = "column-definition")
    protected String columnDefinition;
    @XmlAttribute(name = "table")
    protected String table;
    @XmlAttribute(name = EscapedFunctions.LENGTH)
    protected Integer length;
    @XmlAttribute(name = "precision")
    protected Integer precision;
    @XmlAttribute(name = "scale")
    protected Integer scale;

    public String getComment() {
        return this.comment;
    }

    public void setComment(String value) {
        this.comment = value;
    }

    public String getCheck() {
        return this.check;
    }

    public void setCheck(String value) {
        this.check = value;
    }

    public String getDefault() {
        return this._default;
    }

    public void setDefault(String value) {
        this._default = value;
    }

    public String getRead() {
        return this.read;
    }

    public void setRead(String value) {
        this.read = value;
    }

    public String getWrite() {
        return this.write;
    }

    public void setWrite(String value) {
        this.write = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public Boolean isUnique() {
        return this.unique;
    }

    public void setUnique(Boolean value) {
        this.unique = value;
    }

    public Boolean isNullable() {
        return this.nullable;
    }

    public void setNullable(Boolean value) {
        this.nullable = value;
    }

    public Boolean isInsertable() {
        return this.insertable;
    }

    public void setInsertable(Boolean value) {
        this.insertable = value;
    }

    public Boolean isUpdatable() {
        return this.updatable;
    }

    public void setUpdatable(Boolean value) {
        this.updatable = value;
    }

    public String getColumnDefinition() {
        return this.columnDefinition;
    }

    public void setColumnDefinition(String value) {
        this.columnDefinition = value;
    }

    public String getTable() {
        return this.table;
    }

    public void setTable(String value) {
        this.table = value;
    }

    public Integer getLength() {
        return this.length;
    }

    public void setLength(Integer value) {
        this.length = value;
    }

    public Integer getPrecision() {
        return this.precision;
    }

    public void setPrecision(Integer value) {
        this.precision = value;
    }

    public Integer getScale() {
        return this.scale;
    }

    public void setScale(Integer value) {
        this.scale = value;
    }
}