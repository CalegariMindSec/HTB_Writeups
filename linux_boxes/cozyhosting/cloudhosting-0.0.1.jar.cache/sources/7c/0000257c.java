package org.aspectj.weaver.tools;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/weaver/tools/FuzzyBoolean.class */
public final class FuzzyBoolean {
    private String name;
    public static final FuzzyBoolean YES = new FuzzyBoolean("YES");
    public static final FuzzyBoolean NO = new FuzzyBoolean("NO");
    public static final FuzzyBoolean MAYBE = new FuzzyBoolean("MAYBE");

    public static final FuzzyBoolean fromBoolean(boolean b) {
        return b ? YES : NO;
    }

    public String toString() {
        return this.name;
    }

    private FuzzyBoolean() {
    }

    private FuzzyBoolean(String n) {
        this.name = n;
    }
}