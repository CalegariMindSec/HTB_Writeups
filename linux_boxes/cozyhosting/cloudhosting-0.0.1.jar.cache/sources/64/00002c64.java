package org.hibernate.boot.jaxb.hbm.spi;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "OnDeleteEnum", namespace = "http://www.hibernate.org/xsd/orm/hbm")
@XmlEnum
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/JaxbHbmOnDeleteEnum.class */
public enum JaxbHbmOnDeleteEnum {
    CASCADE("cascade"),
    NOACTION("noaction");
    
    private final String value;

    JaxbHbmOnDeleteEnum(String v) {
        this.value = v;
    }

    public String value() {
        return this.value;
    }

    public static JaxbHbmOnDeleteEnum fromValue(String v) {
        JaxbHbmOnDeleteEnum[] values;
        for (JaxbHbmOnDeleteEnum c : values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}