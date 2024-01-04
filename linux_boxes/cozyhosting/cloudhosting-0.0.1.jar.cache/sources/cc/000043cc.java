package org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/shaded/com/ongres/scram/common/bouncycastle/base64/Encoder.class */
public interface Encoder {
    int encode(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException;

    int decode(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException;

    int decode(String str, OutputStream outputStream) throws IOException;
}