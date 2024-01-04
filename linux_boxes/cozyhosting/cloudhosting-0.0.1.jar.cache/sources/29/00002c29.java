package org.hibernate.boot.jaxb.hbm.spi;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;
import org.postgresql.jdbc.EscapedFunctions;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ColumnType", namespace = "http://www.hibernate.org/xsd/orm/hbm", propOrder = {"comment"})
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/JaxbHbmColumnType.class */
public class JaxbHbmColumnType implements Serializable {
    @XmlElement(namespace = "http://www.hibernate.org/xsd/orm/hbm")
    protected String comment;
    @XmlAttribute(name = "check")
    protected String check;
    @XmlAttribute(name = "default")
    protected String _default;
    @XmlAttribute(name = BeanDefinitionParserDelegate.INDEX_ATTRIBUTE)
    protected String index;
    @XmlAttribute(name = EscapedFunctions.LENGTH)
    protected Integer length;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "not-null")
    protected Boolean notNull;
    @XmlAttribute(name = "precision")
    protected Integer precision;
    @XmlAttribute(name = "read")
    protected String read;
    @XmlAttribute(name = "scale")
    protected Integer scale;
    @XmlAttribute(name = "sql-type")
    protected String sqlType;
    @XmlAttribute(name = "unique")
    protected Boolean unique;
    @XmlAttribute(name = "unique-key")
    protected String uniqueKey;
    @XmlAttribute(name = "write")
    protected String write;

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

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String value) {
        this.index = value;
    }

    public Integer getLength() {
        return this.length;
    }

    public void setLength(Integer value) {
        this.length = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public Boolean isNotNull() {
        return this.notNull;
    }

    public void setNotNull(Boolean value) {
        this.notNull = value;
    }

    public Integer getPrecision() {
        return this.precision;
    }

    public void setPrecision(Integer value) {
        this.precision = value;
    }

    public String getRead() {
        return this.read;
    }

    public void setRead(String value) {
        this.read = value;
    }

    public Integer getScale() {
        return this.scale;
    }

    public void setScale(Integer value) {
        this.scale = value;
    }

    public String getSqlType() {
        return this.sqlType;
    }

    public void setSqlType(String value) {
        this.sqlType = value;
    }

    public Boolean isUnique() {
        return this.unique;
    }

    public void setUnique(Boolean value) {
        this.unique = value;
    }

    public String getUniqueKey() {
        return this.uniqueKey;
    }

    public void setUniqueKey(String value) {
        this.uniqueKey = value;
    }

    public String getWrite() {
        return this.write;
    }

    public void setWrite(String value) {
        this.write = value;
    }
}