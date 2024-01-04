package org.hibernate.dialect;

import org.hibernate.type.SqlTypes;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/dialect/NationalizationSupport.class */
public enum NationalizationSupport {
    IMPLICIT(1, 12, -1, SqlTypes.CLOB),
    EXPLICIT(-15, -9, -16, SqlTypes.NCLOB),
    UNSUPPORTED;
    
    private final int charVariantCode;
    private final int varcharVariantCode;
    private final int longVarcharVariantCode;
    private final int clobVariantCode;

    NationalizationSupport() {
        this(-1, -1, -1, -1);
    }

    NationalizationSupport(int charVariantCode, int varcharVariantCode, int longVarcharVariantCode, int clobVariantCode) {
        this.charVariantCode = charVariantCode;
        this.varcharVariantCode = varcharVariantCode;
        this.longVarcharVariantCode = longVarcharVariantCode;
        this.clobVariantCode = clobVariantCode;
    }

    public int getCharVariantCode() {
        return this.charVariantCode;
    }

    public int getVarcharVariantCode() {
        return this.varcharVariantCode;
    }

    public int getLongVarcharVariantCode() {
        return this.longVarcharVariantCode;
    }

    public int getClobVariantCode() {
        return this.clobVariantCode;
    }
}