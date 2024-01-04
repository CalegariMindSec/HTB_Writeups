package org.hibernate.boot.jaxb.hbm.spi;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "CacheInclusionEnum", namespace = "http://www.hibernate.org/xsd/orm/hbm")
@XmlEnum
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/JaxbHbmCacheInclusionEnum.class */
public enum JaxbHbmCacheInclusionEnum {
    ALL("all"),
    NON_LAZY("non-lazy");
    
    private final String value;

    JaxbHbmCacheInclusionEnum(String v) {
        this.value = v;
    }

    public String value() {
        return this.value;
    }

    public static JaxbHbmCacheInclusionEnum fromValue(String v) {
        JaxbHbmCacheInclusionEnum[] values;
        for (JaxbHbmCacheInclusionEnum c : values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}