package org.hibernate.boot.jaxb.hbm.spi;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "OuterJoinEnum", namespace = "http://www.hibernate.org/xsd/orm/hbm")
@XmlEnum
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/JaxbHbmOuterJoinEnum.class */
public enum JaxbHbmOuterJoinEnum {
    AUTO("auto"),
    FALSE("false"),
    TRUE("true");
    
    private final String value;

    JaxbHbmOuterJoinEnum(String v) {
        this.value = v;
    }

    public String value() {
        return this.value;
    }

    public static JaxbHbmOuterJoinEnum fromValue(String v) {
        JaxbHbmOuterJoinEnum[] values;
        for (JaxbHbmOuterJoinEnum c : values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}