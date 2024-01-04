package org.hibernate.boot.jaxb.hbm.spi;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "UnsavedValueVersionEnum", namespace = "http://www.hibernate.org/xsd/orm/hbm")
@XmlEnum
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/JaxbHbmUnsavedValueVersionEnum.class */
public enum JaxbHbmUnsavedValueVersionEnum {
    NEGATIVE("negative"),
    NULL("null"),
    UNDEFINED("undefined");
    
    private final String value;

    JaxbHbmUnsavedValueVersionEnum(String v) {
        this.value = v;
    }

    public String value() {
        return this.value;
    }

    public static JaxbHbmUnsavedValueVersionEnum fromValue(String v) {
        JaxbHbmUnsavedValueVersionEnum[] values;
        for (JaxbHbmUnsavedValueVersionEnum c : values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}