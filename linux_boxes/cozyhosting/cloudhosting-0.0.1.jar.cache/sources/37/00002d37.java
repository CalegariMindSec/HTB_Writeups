package org.hibernate.boot.jaxb.mapping;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "singular-fetch-mode", namespace = "http://www.hibernate.org/xsd/orm/mapping")
@XmlEnum
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/mapping/JaxbSingularFetchMode.class */
public enum JaxbSingularFetchMode {
    SELECT,
    JOIN;

    public String value() {
        return name();
    }

    public static JaxbSingularFetchMode fromValue(String v) {
        return valueOf(v);
    }
}