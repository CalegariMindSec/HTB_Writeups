package org.hibernate.boot.jaxb.hbm.spi;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "LazyWithExtraEnum", namespace = "http://www.hibernate.org/xsd/orm/hbm")
@XmlEnum
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/JaxbHbmLazyWithExtraEnum.class */
public enum JaxbHbmLazyWithExtraEnum {
    EXTRA("extra"),
    FALSE("false"),
    TRUE("true");
    
    private final String value;

    JaxbHbmLazyWithExtraEnum(String v) {
        this.value = v;
    }

    public String value() {
        return this.value;
    }

    public static JaxbHbmLazyWithExtraEnum fromValue(String v) {
        JaxbHbmLazyWithExtraEnum[] values;
        for (JaxbHbmLazyWithExtraEnum c : values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}