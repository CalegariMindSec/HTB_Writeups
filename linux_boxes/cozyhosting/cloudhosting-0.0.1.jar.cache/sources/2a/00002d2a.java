package org.hibernate.boot.jaxb.mapping;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "plural-fetch-mode", namespace = "http://www.hibernate.org/xsd/orm/mapping")
@XmlEnum
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/mapping/JaxbPluralFetchMode.class */
public enum JaxbPluralFetchMode {
    SELECT,
    JOIN,
    SUBSELECT;

    public String value() {
        return name();
    }

    public static JaxbPluralFetchMode fromValue(String v) {
        return valueOf(v);
    }
}