package org.hibernate.tool.schema;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/tool/schema/SourceType.class */
public enum SourceType {
    METADATA("metadata"),
    SCRIPT("script"),
    METADATA_THEN_SCRIPT("metadata-then-script"),
    SCRIPT_THEN_METADATA("script-then-metadata");
    
    private final String externalName;

    SourceType(String externalName) {
        this.externalName = externalName;
    }

    public static SourceType interpret(Object value, SourceType defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof SourceType) {
            return (SourceType) value;
        }
        String name = value.toString().trim();
        if (name.isEmpty()) {
            return defaultValue;
        }
        if (METADATA.externalName.equals(value)) {
            return METADATA;
        }
        if (SCRIPT.externalName.equals(value)) {
            return SCRIPT;
        }
        if (METADATA_THEN_SCRIPT.externalName.equals(value)) {
            return METADATA_THEN_SCRIPT;
        }
        if (SCRIPT_THEN_METADATA.externalName.equals(value)) {
            return SCRIPT_THEN_METADATA;
        }
        throw new IllegalArgumentException("Unrecognized schema generation source-type value : '" + value + "'");
    }
}