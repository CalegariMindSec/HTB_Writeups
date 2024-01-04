package org.glassfish.jaxb.core.marshaller;

import java.io.IOException;
import java.io.Writer;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jaxb-core-4.0.1.jar:org/glassfish/jaxb/core/marshaller/CharacterEscapeHandler.class */
public interface CharacterEscapeHandler {
    void escape(char[] cArr, int i, int i2, boolean z, Writer writer) throws IOException;
}