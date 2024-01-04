package org.hibernate.annotations;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/annotations/SourceType.class */
public enum SourceType {
    VM("timestamp"),
    DB("dbtimestamp");
    
    private final String typeName;

    SourceType(String typeName) {
        this.typeName = typeName;
    }

    public String typeName() {
        return this.typeName;
    }
}