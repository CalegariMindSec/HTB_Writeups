package org.aspectj.lang.reflect;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/lang/reflect/NoSuchAdviceException.class */
public class NoSuchAdviceException extends Exception {
    private static final long serialVersionUID = 3256444698657634352L;
    private String name;

    public NoSuchAdviceException(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}