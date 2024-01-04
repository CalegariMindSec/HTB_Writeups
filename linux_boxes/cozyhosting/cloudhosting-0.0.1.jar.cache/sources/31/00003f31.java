package org.hibernate.tool.schema;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/tool/schema/Action.class */
public enum Action {
    NONE("none"),
    CREATE_ONLY("create", "create-only"),
    DROP("drop"),
    CREATE("drop-and-create", "create"),
    CREATE_DROP(null, "create-drop"),
    VALIDATE(null, "validate"),
    UPDATE(null, "update");
    
    private final String externalJpaName;
    private final String externalHbm2ddlName;

    Action(String externalJpaName) {
        this(externalJpaName, externalJpaName);
    }

    Action(String externalJpaName, String externalHbm2ddlName) {
        this.externalJpaName = externalJpaName;
        this.externalHbm2ddlName = externalHbm2ddlName;
    }

    public String getExternalJpaName() {
        return this.externalJpaName;
    }

    public String getExternalHbm2ddlName() {
        return this.externalHbm2ddlName;
    }

    @Override // java.lang.Enum
    public String toString() {
        return getClass().getSimpleName() + "(externalJpaName=" + this.externalJpaName + ", externalHbm2ddlName=" + this.externalHbm2ddlName + ")";
    }

    public static Action interpretJpaSetting(Object value) {
        Action[] values;
        Action[] values2;
        Action[] values3;
        if (value == null) {
            return NONE;
        }
        if (value instanceof Action) {
            return (Action) value;
        }
        String name = value.toString().trim();
        if (name.isEmpty() || NONE.externalJpaName.equals(name)) {
            return NONE;
        }
        for (Action action : values()) {
            if (action.externalJpaName != null && action.externalJpaName.equals(name)) {
                return action;
            }
        }
        for (Action action2 : values()) {
            if (action2.externalHbm2ddlName != null && action2.externalHbm2ddlName.equals(name)) {
                return action2;
            }
        }
        for (Action action3 : values()) {
            if (action3.name().equals(name)) {
                return action3;
            }
        }
        throw new IllegalArgumentException("Unrecognized JPA schema generation action value : " + value);
    }

    public static Action interpretHbm2ddlSetting(Object value) {
        Action[] values;
        Action[] values2;
        if (value == null) {
            return NONE;
        }
        if (value instanceof Action) {
            return hbm2ddlSetting((Action) value);
        }
        String name = value.toString().trim();
        if (name.isEmpty() || NONE.externalJpaName.equals(name)) {
            return NONE;
        }
        for (Action action : values()) {
            if (action.externalHbm2ddlName != null && action.externalHbm2ddlName.equals(name)) {
                return hbm2ddlSetting(action);
            }
        }
        for (Action action2 : values()) {
            if (action2.externalJpaName != null && action2.externalJpaName.equals(name)) {
                return hbm2ddlSetting(action2);
            }
        }
        throw new IllegalArgumentException("Unrecognized legacy `hibernate.hbm2ddl.auto` value : `" + value + "`");
    }

    private static Action hbm2ddlSetting(Action action) {
        return action;
    }
}