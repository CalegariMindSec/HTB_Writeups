package org.hibernate.boot.model.source.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/model/source/spi/PluralAttributeElementNature.class */
public enum PluralAttributeElementNature {
    BASIC(false, false),
    AGGREGATE(false, true),
    ONE_TO_MANY,
    MANY_TO_MANY,
    MANY_TO_ANY;
    
    private final boolean isAssociation;
    private final boolean isCascadeable;

    PluralAttributeElementNature() {
        this(true, true);
    }

    PluralAttributeElementNature(boolean association, boolean cascadeable) {
        this.isAssociation = association;
        this.isCascadeable = cascadeable;
    }

    public boolean isAssociation() {
        return this.isAssociation;
    }

    public boolean isCascadeable() {
        return this.isCascadeable;
    }
}