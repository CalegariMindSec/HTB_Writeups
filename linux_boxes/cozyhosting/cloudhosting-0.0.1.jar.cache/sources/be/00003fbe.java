package org.hibernate.tool.schema.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/tool/schema/spi/ScriptTargetOutput.class */
public interface ScriptTargetOutput {
    void prepare();

    void accept(String str);

    void release();
}