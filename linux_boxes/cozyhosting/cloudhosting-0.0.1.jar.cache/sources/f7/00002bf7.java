package org.hibernate.boot.jaxb;

import org.hibernate.cfg.AnnotatedDiscriminatorColumn;
import org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.engine.XMLDeclaration;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/jaxb/SourceType.class */
public enum SourceType {
    RESOURCE(DefaultBeanDefinitionDocumentReader.RESOURCE_ATTRIBUTE),
    FILE("file"),
    INPUT_STREAM("input stream"),
    URL("URL"),
    STRING(AnnotatedDiscriminatorColumn.DEFAULT_DISCRIMINATOR_TYPE),
    DOM(XMLDeclaration.DEFAULT_KEYWORD),
    JAR(ResourceUtils.URL_PROTOCOL_JAR),
    ANNOTATION("annotation"),
    OTHER("other");
    
    private final String legacyTypeText;

    SourceType(String legacyTypeText) {
        this.legacyTypeText = legacyTypeText;
    }

    public String getLegacyTypeText() {
        return this.legacyTypeText;
    }
}