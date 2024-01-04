package org.hibernate.boot.jaxb.hbm.spi;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "FetchStyleEnum", namespace = "http://www.hibernate.org/xsd/orm/hbm")
@XmlEnum
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/JaxbHbmFetchStyleEnum.class */
public enum JaxbHbmFetchStyleEnum {
    JOIN("join"),
    SELECT("select");
    
    private final String value;

    JaxbHbmFetchStyleEnum(String v) {
        this.value = v;
    }

    public String value() {
        return this.value;
    }

    public static JaxbHbmFetchStyleEnum fromValue(String v) {
        JaxbHbmFetchStyleEnum[] values;
        for (JaxbHbmFetchStyleEnum c : values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}