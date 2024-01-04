package org.hibernate.boot.jaxb.hbm.spi;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "LazyEnum", namespace = "http://www.hibernate.org/xsd/orm/hbm")
@XmlEnum
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/JaxbHbmLazyEnum.class */
public enum JaxbHbmLazyEnum {
    FALSE("false"),
    PROXY("proxy");
    
    private final String value;

    JaxbHbmLazyEnum(String v) {
        this.value = v;
    }

    public String value() {
        return this.value;
    }

    public static JaxbHbmLazyEnum fromValue(String v) {
        JaxbHbmLazyEnum[] values;
        for (JaxbHbmLazyEnum c : values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}