package org.hibernate.boot.jaxb.cfg.spi;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;
import org.hibernate.boot.cfgxml.internal.JaxbCfgProcessor;

@XmlType(name = "CacheUsageEnum", namespace = JaxbCfgProcessor.HIBERNATE_CONFIGURATION_URI)
@XmlEnum
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/cfg/spi/JaxbCfgCacheUsageEnum.class */
public enum JaxbCfgCacheUsageEnum {
    NONSTRICT_READ_WRITE("nonstrict-read-write"),
    READ_ONLY("read-only"),
    READ_WRITE("read-write"),
    TRANSACTIONAL("transactional");
    
    private final String value;

    JaxbCfgCacheUsageEnum(String v) {
        this.value = v;
    }

    public String value() {
        return this.value;
    }

    public static JaxbCfgCacheUsageEnum fromValue(String v) {
        JaxbCfgCacheUsageEnum[] values;
        for (JaxbCfgCacheUsageEnum c : values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}