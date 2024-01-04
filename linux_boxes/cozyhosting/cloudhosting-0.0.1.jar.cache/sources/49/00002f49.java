package org.hibernate.boot.xsd;

import javax.xml.validation.Schema;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/xsd/XsdDescriptor.class */
public final class XsdDescriptor {
    private final String localResourceName;
    private final String namespaceUri;
    private final String version;
    private final Schema schema;

    /* JADX INFO: Access modifiers changed from: package-private */
    public XsdDescriptor(String localResourceName, Schema schema, String version, String namespaceUri) {
        this.localResourceName = localResourceName;
        this.schema = schema;
        this.version = version;
        this.namespaceUri = namespaceUri;
    }

    public String getLocalResourceName() {
        return this.localResourceName;
    }

    public String getNamespaceUri() {
        return this.namespaceUri;
    }

    public String getVersion() {
        return this.version;
    }

    public Schema getSchema() {
        return this.schema;
    }
}