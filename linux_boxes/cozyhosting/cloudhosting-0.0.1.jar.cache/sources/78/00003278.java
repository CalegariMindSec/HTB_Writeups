package org.hibernate.engine;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/OptimisticLockStyle.class */
public enum OptimisticLockStyle {
    NONE(-1),
    VERSION(0),
    DIRTY(1),
    ALL(2);
    
    private final int oldCode;

    OptimisticLockStyle(int oldCode) {
        this.oldCode = oldCode;
    }

    public int getOldCode() {
        return this.oldCode;
    }

    public boolean isAllOrDirty() {
        return isAll() || isDirty();
    }

    public boolean isAll() {
        return this == ALL;
    }

    public boolean isDirty() {
        return this == DIRTY;
    }

    public boolean isVersion() {
        return this == VERSION;
    }

    public boolean isNone() {
        return this == NONE;
    }

    public static OptimisticLockStyle interpretOldCode(int oldCode) {
        switch (oldCode) {
            case -1:
                return NONE;
            case 0:
                return VERSION;
            case 1:
                return DIRTY;
            case 2:
                return ALL;
            default:
                throw new IllegalArgumentException("Illegal legacy optimistic lock style code : " + oldCode);
        }
    }
}