package org.hibernate.mapping;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/mapping/MetaAttribute.class */
public class MetaAttribute implements Serializable {
    private String name;
    private java.util.List values = new ArrayList();

    public MetaAttribute(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public java.util.List getValues() {
        return Collections.unmodifiableList(this.values);
    }

    public void addValue(String value) {
        this.values.add(value);
    }

    public String getValue() {
        if (this.values.size() != 1) {
            throw new IllegalStateException("no unique value");
        }
        return (String) this.values.get(0);
    }

    public boolean isMultiValued() {
        return this.values.size() > 1;
    }

    public String toString() {
        return "[" + this.name + "=" + this.values + "]";
    }
}