package org.hibernate.metamodel;

import java.util.Locale;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/metamodel/RepresentationMode.class */
public enum RepresentationMode {
    POJO("pojo"),
    MAP("dynamic-map", BeanDefinitionParserDelegate.MAP_ELEMENT);
    
    private final String externalName;
    private final String alternativeExternalName;

    RepresentationMode(String externalName) {
        this(externalName, null);
    }

    RepresentationMode(String externalName, String alternativeExternalName) {
        this.externalName = externalName;
        this.alternativeExternalName = alternativeExternalName;
    }

    public String getExternalName() {
        return this.externalName;
    }

    public static RepresentationMode fromExternalName(String externalName) {
        if (externalName == null) {
            return POJO;
        }
        if (MAP.externalName.equalsIgnoreCase(externalName) || MAP.alternativeExternalName.equalsIgnoreCase(externalName)) {
            return MAP;
        }
        if (POJO.externalName.equalsIgnoreCase(externalName)) {
            return POJO;
        }
        return valueOf(externalName.toUpperCase(Locale.ROOT));
    }
}