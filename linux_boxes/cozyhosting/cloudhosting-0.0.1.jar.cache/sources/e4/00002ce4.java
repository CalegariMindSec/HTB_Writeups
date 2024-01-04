package org.hibernate.boot.jaxb.mapping;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "cache-inclusion-type", namespace = "http://www.hibernate.org/xsd/orm/mapping")
@XmlEnum
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/mapping/JaxbCacheInclusionType.class */
public enum JaxbCacheInclusionType {
    ALL("all"),
    NON_LAZY("non-lazy");
    
    private final String value;

    JaxbCacheInclusionType(String v) {
        this.value = v;
    }

    public String value() {
        return this.value;
    }

    public static JaxbCacheInclusionType fromValue(String v) {
        JaxbCacheInclusionType[] values;
        for (JaxbCacheInclusionType c : values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}