package org.hibernate.annotations;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/annotations/OnDeleteAction.class */
public enum OnDeleteAction {
    NO_ACTION("no-action"),
    CASCADE("cascade");
    
    private final String alternativeName;

    OnDeleteAction(String alternativeName) {
        this.alternativeName = alternativeName;
    }

    public String getAlternativeName() {
        return this.alternativeName;
    }

    public static OnDeleteAction fromExternalForm(Object value) {
        OnDeleteAction[] values;
        if (value == null) {
            return null;
        }
        if (value instanceof OnDeleteAction) {
            return (OnDeleteAction) value;
        }
        String valueString = value.toString();
        try {
            return valueOf(valueString);
        } catch (IllegalArgumentException e) {
            for (OnDeleteAction checkAction : values()) {
                if (checkAction.alternativeName.equalsIgnoreCase(valueString)) {
                    return checkAction;
                }
            }
            return null;
        }
    }
}