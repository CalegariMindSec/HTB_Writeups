package org.glassfish.jaxb.runtime.v2.runtime;

import com.sun.istack.NotNull;
import javax.xml.namespace.NamespaceContext;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jaxb-runtime-4.0.1.jar:org/glassfish/jaxb/runtime/v2/runtime/NamespaceContext2.class */
public interface NamespaceContext2 extends NamespaceContext {
    String declareNamespace(String str, String str2, boolean z);

    int force(@NotNull String str, @NotNull String str2);
}