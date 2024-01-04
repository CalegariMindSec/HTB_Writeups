package org.hibernate.tool.schema.spi;

import java.io.Reader;
import java.net.URL;
import java.util.List;
import java.util.function.Function;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/tool/schema/spi/ScriptSourceInput.class */
public interface ScriptSourceInput {
    List<String> extract(Function<Reader, List<String>> function);

    default boolean containsScript(URL url) {
        return false;
    }
}