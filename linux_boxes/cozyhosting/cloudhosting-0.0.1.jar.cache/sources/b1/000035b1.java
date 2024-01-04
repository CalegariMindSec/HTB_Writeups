package org.hibernate.graph;

import java.util.Locale;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/graph/GraphSemantic.class */
public enum GraphSemantic {
    FETCH("jakarta.persistence.fetchgraph", "javax.persistence.fetchgraph"),
    LOAD("jakarta.persistence.loadgraph", "javax.persistence.loadgraph");
    
    private final String jakartaHintName;
    private final String javaeeHintName;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !GraphSemantic.class.desiredAssertionStatus();
    }

    GraphSemantic(String jakartaHintName, String javaeeHintName) {
        this.jakartaHintName = jakartaHintName;
        this.javaeeHintName = javaeeHintName;
    }

    public String getJakartaHintName() {
        return this.jakartaHintName;
    }

    @Deprecated(since = "6.0")
    public String getJpaHintName() {
        return this.javaeeHintName;
    }

    public static GraphSemantic fromHintName(String hintName) {
        if ($assertionsDisabled || hintName != null) {
            if (FETCH.getJakartaHintName().equals(hintName) || FETCH.getJpaHintName().equals(hintName)) {
                return FETCH;
            }
            if (LOAD.getJakartaHintName().equalsIgnoreCase(hintName) || LOAD.getJpaHintName().equalsIgnoreCase(hintName)) {
                return LOAD;
            }
            throw new IllegalArgumentException(String.format(Locale.ROOT, "Unknown EntityGraph hint name - `%s`.  Expecting `%s` or `%s` (or `%s` and `%s`).", hintName, FETCH.jakartaHintName, LOAD.jakartaHintName, FETCH.javaeeHintName, LOAD.javaeeHintName));
        }
        throw new AssertionError();
    }

    @Deprecated(since = "6.0")
    public static GraphSemantic fromJpaHintName(String hintName) {
        return fromHintName(hintName);
    }
}