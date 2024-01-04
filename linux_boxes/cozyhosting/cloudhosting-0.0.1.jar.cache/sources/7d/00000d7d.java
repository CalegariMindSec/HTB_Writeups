package lombok.eclipse.agent;

/* JADX WARN: Classes with same name are omitted:
  cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:Class50/lombok/eclipse/agent/EclipseLoaderPatcherTransplants.SCL.lombok
 */
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:lombok/eclipse/agent/EclipseLoaderPatcherTransplants.SCL.lombok */
public class EclipseLoaderPatcherTransplants {
    static Class class$0;
    static Class class$1;
    static Class class$2;
    static Class class$3;
    static Class class$4;

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: JavaClassParseException: Unknown opcode: 0xa8 in method: lombok.eclipse.agent.EclipseLoaderPatcherTransplants.overrideLoadResult(java.lang.ClassLoader, java.lang.String, boolean):java.lang.Class, file: cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:lombok/eclipse/agent/EclipseLoaderPatcherTransplants.SCL.lombok
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:307)
        Caused by: jadx.plugins.input.java.utils.JavaClassParseException: Unknown opcode: 0xa8
        	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:71)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	... 5 more
        */
    public static java.lang.Class overrideLoadResult(java.lang.ClassLoader r0, java.lang.String r1, boolean r2) throws java.lang.ClassNotFoundException {
        /*
        // Can't load method instructions: Load method exception: JavaClassParseException: Unknown opcode: 0xa8 in method: lombok.eclipse.agent.EclipseLoaderPatcherTransplants.overrideLoadResult(java.lang.ClassLoader, java.lang.String, boolean):java.lang.Class, file: cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:lombok/eclipse/agent/EclipseLoaderPatcherTransplants.SCL.lombok
        */
        throw new UnsupportedOperationException("Method not decompiled: lombok.eclipse.agent.EclipseLoaderPatcherTransplants.overrideLoadResult(java.lang.ClassLoader, java.lang.String, boolean):java.lang.Class");
    }

    public static boolean overrideLoadDecide(ClassLoader original, String name, boolean resolve) {
        return name.startsWith("lombok.");
    }
}