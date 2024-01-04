package org.aspectj.weaver;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/weaver/IUnwovenClassFile.class */
public interface IUnwovenClassFile {
    String getFilename();

    String getClassName();

    byte[] getBytes();

    char[] getClassNameAsChars();
}