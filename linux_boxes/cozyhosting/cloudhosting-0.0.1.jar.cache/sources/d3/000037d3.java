package org.hibernate.mapping;

import java.io.Serializable;
import java.util.Properties;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/mapping/TypeDef.class */
public class TypeDef implements Serializable {
    private String typeClass;
    private Properties parameters;

    public TypeDef(String typeClass, Properties parameters) {
        this.typeClass = typeClass;
        this.parameters = parameters;
    }

    public Properties getParameters() {
        return this.parameters;
    }

    public String getTypeClass() {
        return this.typeClass;
    }
}