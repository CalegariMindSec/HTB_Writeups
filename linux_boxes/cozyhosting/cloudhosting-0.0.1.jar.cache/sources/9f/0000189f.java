package net.bytebuddy.jar.asm.signature;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/jar/asm/signature/SignatureVisitor.class */
public abstract class SignatureVisitor {
    public static final char EXTENDS = '+';
    public static final char SUPER = '-';
    public static final char INSTANCEOF = '=';
    protected final int api;

    /* JADX INFO: Access modifiers changed from: protected */
    public SignatureVisitor(int api) {
        if (api != 589824 && api != 524288 && api != 458752 && api != 393216 && api != 327680 && api != 262144 && api != 17432576) {
            throw new IllegalArgumentException("Unsupported api " + api);
        }
        this.api = api;
    }

    public void visitFormalTypeParameter(String name) {
    }

    public SignatureVisitor visitClassBound() {
        return this;
    }

    public SignatureVisitor visitInterfaceBound() {
        return this;
    }

    public SignatureVisitor visitSuperclass() {
        return this;
    }

    public SignatureVisitor visitInterface() {
        return this;
    }

    public SignatureVisitor visitParameterType() {
        return this;
    }

    public SignatureVisitor visitReturnType() {
        return this;
    }

    public SignatureVisitor visitExceptionType() {
        return this;
    }

    public void visitBaseType(char descriptor) {
    }

    public void visitTypeVariable(String name) {
    }

    public SignatureVisitor visitArrayType() {
        return this;
    }

    public void visitClassType(String name) {
    }

    public void visitInnerClassType(String name) {
    }

    public void visitTypeArgument() {
    }

    public SignatureVisitor visitTypeArgument(char wildcard) {
        return this;
    }

    public void visitEnd() {
    }
}