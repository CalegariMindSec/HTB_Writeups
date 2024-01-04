package org.hibernate.boot.jaxb.cfg.spi;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;
import org.aspectj.lang.JoinPoint;
import org.hibernate.boot.cfgxml.internal.JaxbCfgProcessor;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

@XmlType(name = "EventTypeEnum", namespace = JaxbCfgProcessor.HIBERNATE_CONFIGURATION_URI)
@XmlEnum
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/cfg/spi/JaxbCfgEventTypeEnum.class */
public enum JaxbCfgEventTypeEnum {
    AUTO_FLUSH("auto-flush"),
    CREATE("create"),
    CREATE_ONFLUSH("create-onflush"),
    DELETE("delete"),
    DIRTY_CHECK("dirty-check"),
    EVICT("evict"),
    FLUSH("flush"),
    FLUSH_ENTITY("flush-entity"),
    LOAD("load"),
    LOAD_COLLECTION("load-collection"),
    LOCK(JoinPoint.SYNCHRONIZATION_LOCK),
    MERGE(BeanDefinitionParserDelegate.MERGE_ATTRIBUTE),
    POST_COLLECTION_RECREATE("post-collection-recreate"),
    POST_COLLECTION_REMOVE("post-collection-remove"),
    POST_COLLECTION_UPDATE("post-collection-update"),
    POST_COMMIT_DELETE("post-commit-delete"),
    POST_COMMIT_INSERT("post-commit-insert"),
    POST_COMMIT_UPDATE("post-commit-update"),
    POST_DELETE("post-delete"),
    POST_INSERT("post-insert"),
    POST_LOAD("post-load"),
    POST_UPDATE("post-update"),
    PRE_COLLECTION_RECREATE("pre-collection-recreate"),
    PRE_COLLECTION_REMOVE("pre-collection-remove"),
    PRE_COLLECTION_UPDATE("pre-collection-update"),
    PRE_DELETE("pre-delete"),
    PRE_INSERT("pre-insert"),
    PRE_LOAD("pre-load"),
    PRE_UPDATE("pre-update"),
    REFRESH("refresh"),
    REPLICATE("replicate"),
    SAVE("save"),
    SAVE_UPDATE("save-update"),
    UPDATE("update");
    
    private final String value;

    JaxbCfgEventTypeEnum(String v) {
        this.value = v;
    }

    public String value() {
        return this.value;
    }

    public static JaxbCfgEventTypeEnum fromValue(String v) {
        JaxbCfgEventTypeEnum[] values;
        for (JaxbCfgEventTypeEnum c : values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}