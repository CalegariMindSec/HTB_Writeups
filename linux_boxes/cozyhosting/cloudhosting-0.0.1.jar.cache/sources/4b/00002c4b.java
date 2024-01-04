package org.hibernate.boot.jaxb.hbm.spi;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "LazyWithNoProxyEnum", namespace = "http://www.hibernate.org/xsd/orm/hbm")
@XmlEnum
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/hbm/spi/JaxbHbmLazyWithNoProxyEnum.class */
public enum JaxbHbmLazyWithNoProxyEnum {
    FALSE("false"),
    NO_PROXY("no-proxy"),
    PROXY("proxy");
    
    private final String value;

    JaxbHbmLazyWithNoProxyEnum(String v) {
        this.value = v;
    }

    public String value() {
        return this.value;
    }

    public static JaxbHbmLazyWithNoProxyEnum fromValue(String v) {
        JaxbHbmLazyWithNoProxyEnum[] values;
        for (JaxbHbmLazyWithNoProxyEnum c : values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}