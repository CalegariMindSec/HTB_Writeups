package org.hibernate.tool.schema.internal.exec;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/tool/schema/internal/exec/GenerationTarget.class */
public interface GenerationTarget {
    void prepare();

    void accept(String str);

    void release();
}