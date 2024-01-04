package org.hibernate.internal.util.securitymanager;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/securitymanager/SystemSecurityManager.class */
public final class SystemSecurityManager {
    public static final String FULLY_DISABLE_PROP_NAME = "org.hibernate.internal.util.securitymanager.FULLY_DISABLE";
    private static final boolean disabledForced = Boolean.getBoolean(FULLY_DISABLE_PROP_NAME);
    private static final boolean SM_IS_ENABLED;

    static {
        SM_IS_ENABLED = (disabledForced || System.getSecurityManager() == null) ? false : true;
    }

    public static boolean isSecurityManagerEnabled() {
        return SM_IS_ENABLED;
    }
}