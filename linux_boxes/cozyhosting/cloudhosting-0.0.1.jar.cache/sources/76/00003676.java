package org.hibernate.internal.util;

import java.io.Serializable;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/MarkerObject.class */
public class MarkerObject implements Serializable {
    private String name;

    public MarkerObject(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}