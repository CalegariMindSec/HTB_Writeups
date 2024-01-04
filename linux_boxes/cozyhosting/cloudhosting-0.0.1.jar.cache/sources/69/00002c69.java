package org.hibernate.boot.jaxb.hbm.spi;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "PolymorphismEnum", namespace = "http://www.hibernate.org/xsd/orm/hbm")
@XmlEnum
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/JaxbHbmPolymorphismEnum.class */
public enum JaxbHbmPolymorphismEnum {
    EXPLICIT("explicit"),
    IMPLICIT("implicit");
    
    private final String value;

    JaxbHbmPolymorphismEnum(String v) {
        this.value = v;
    }

    public String value() {
        return this.value;
    }

    public static JaxbHbmPolymorphismEnum fromValue(String v) {
        JaxbHbmPolymorphismEnum[] values;
        for (JaxbHbmPolymorphismEnum c : values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}