package org.hibernate.boot.jaxb.hbm.spi;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "FetchStyleWithSubselectEnum", namespace = "http://www.hibernate.org/xsd/orm/hbm")
@XmlEnum
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/JaxbHbmFetchStyleWithSubselectEnum.class */
public enum JaxbHbmFetchStyleWithSubselectEnum {
    JOIN("join"),
    SELECT("select"),
    SUBSELECT("subselect");
    
    private final String value;

    JaxbHbmFetchStyleWithSubselectEnum(String v) {
        this.value = v;
    }

    public String value() {
        return this.value;
    }

    public static JaxbHbmFetchStyleWithSubselectEnum fromValue(String v) {
        JaxbHbmFetchStyleWithSubselectEnum[] values;
        for (JaxbHbmFetchStyleWithSubselectEnum c : values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}