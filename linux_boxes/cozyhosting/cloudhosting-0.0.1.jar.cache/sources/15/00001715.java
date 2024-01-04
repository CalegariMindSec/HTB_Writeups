package net.bytebuddy.implementation.attribute;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/implementation/attribute/AnnotationRetention.class */
public enum AnnotationRetention {
    ENABLED(true),
    DISABLED(false);
    
    private final boolean enabled;

    AnnotationRetention(boolean enabled) {
        this.enabled = enabled;
    }

    public static AnnotationRetention of(boolean enabled) {
        return enabled ? ENABLED : DISABLED;
    }

    public boolean isEnabled() {
        return this.enabled;
    }
}