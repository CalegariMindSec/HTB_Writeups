package org.aspectj.weaver;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/weaver/RuntimeVersion.class */
public enum RuntimeVersion {
    V1_2(Constants.RUNTIME_LEVEL_12),
    V1_5("1.5"),
    V1_6_10("1.6.10"),
    V1_9(Constants.RUNTIME_LEVEL_19);
    
    private String[] aliases;

    RuntimeVersion(String... aliases) {
        this.aliases = null;
        this.aliases = aliases;
    }

    public static RuntimeVersion getVersionFor(String version) {
        RuntimeVersion[] values;
        String[] strArr;
        for (RuntimeVersion candidateVersion : values()) {
            if (candidateVersion.name().equals(version)) {
                return candidateVersion;
            }
            if (candidateVersion.aliases != null) {
                for (String alias : candidateVersion.aliases) {
                    if (alias.equals(version)) {
                        return candidateVersion;
                    }
                }
                continue;
            }
        }
        return null;
    }

    public boolean isThisVersionOrLater(RuntimeVersion version) {
        return compareTo(version) >= 0;
    }
}