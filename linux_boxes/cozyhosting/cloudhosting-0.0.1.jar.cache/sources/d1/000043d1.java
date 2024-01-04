package org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/shaded/com/ongres/scram/common/bouncycastle/pbkdf2/Digest.class */
public interface Digest {
    String getAlgorithmName();

    int getDigestSize();

    void update(byte b);

    void update(byte[] bArr, int i, int i2);

    int doFinal(byte[] bArr, int i);

    void reset();
}