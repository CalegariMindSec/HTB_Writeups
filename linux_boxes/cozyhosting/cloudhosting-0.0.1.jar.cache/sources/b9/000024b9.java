package org.aspectj.weaver.bcel.asm;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/weaver/bcel/asm/AsmDetector.class */
public class AsmDetector {
    public static final String CLASS_READER = "aj.org.objectweb.asm.ClassReader";
    public static final String CLASS_VISITOR = "aj.org.objectweb.asm.ClassVisitor";
    public static boolean isAsmAround;
    public static Throwable reasonAsmIsMissing;

    static {
        try {
            Class<?> reader = Class.forName(CLASS_READER);
            Class<?> visitor = Class.forName(CLASS_VISITOR);
            reader.getMethod("accept", visitor, Integer.TYPE);
            isAsmAround = true;
        } catch (Exception e) {
            isAsmAround = false;
            reasonAsmIsMissing = e;
        }
    }
}