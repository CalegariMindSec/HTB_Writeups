package lombok;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:lombok/AccessLevel.class */
public enum AccessLevel {
    PUBLIC,
    MODULE,
    PROTECTED,
    PACKAGE,
    PRIVATE,
    NONE;

    /* renamed from: values  reason: to resolve conflict with enum method */
    public static AccessLevel[] valuesCustom() {
        AccessLevel[] valuesCustom = values();
        int length = valuesCustom.length;
        AccessLevel[] accessLevelArr = new AccessLevel[length];
        System.arraycopy(valuesCustom, 0, accessLevelArr, 0, length);
        return accessLevelArr;
    }
}