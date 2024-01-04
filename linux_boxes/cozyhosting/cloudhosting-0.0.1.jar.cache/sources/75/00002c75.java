package org.hibernate.boot.jaxb.hbm.spi;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "TimestampSourceEnum", namespace = "http://www.hibernate.org/xsd/orm/hbm")
@XmlEnum
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/JaxbHbmTimestampSourceEnum.class */
public enum JaxbHbmTimestampSourceEnum {
    DB("db"),
    VM("vm");
    
    private final String value;

    JaxbHbmTimestampSourceEnum(String v) {
        this.value = v;
    }

    public String value() {
        return this.value;
    }

    public static JaxbHbmTimestampSourceEnum fromValue(String v) {
        JaxbHbmTimestampSourceEnum[] values;
        for (JaxbHbmTimestampSourceEnum c : values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}