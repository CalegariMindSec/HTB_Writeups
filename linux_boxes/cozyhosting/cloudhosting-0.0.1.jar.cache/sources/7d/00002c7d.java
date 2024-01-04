package org.hibernate.boot.jaxb.hbm.spi;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "UnsavedValueTimestampEnum", namespace = "http://www.hibernate.org/xsd/orm/hbm")
@XmlEnum
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/JaxbHbmUnsavedValueTimestampEnum.class */
public enum JaxbHbmUnsavedValueTimestampEnum {
    NULL("null"),
    UNDEFINED("undefined");
    
    private final String value;

    JaxbHbmUnsavedValueTimestampEnum(String v) {
        this.value = v;
    }

    public String value() {
        return this.value;
    }

    public static JaxbHbmUnsavedValueTimestampEnum fromValue(String v) {
        JaxbHbmUnsavedValueTimestampEnum[] values;
        for (JaxbHbmUnsavedValueTimestampEnum c : values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}