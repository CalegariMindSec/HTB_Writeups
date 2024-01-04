package com.sun.xml.txw2.output;

import java.io.IOException;
import java.io.Writer;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/txw2-4.0.1.jar:com/sun/xml/txw2/output/CharacterEscapeHandler.class */
public interface CharacterEscapeHandler {
    void escape(char[] cArr, int i, int i2, boolean z, Writer writer) throws IOException;
}