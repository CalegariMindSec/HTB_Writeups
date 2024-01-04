package jakarta.persistence.spi;

import java.security.ProtectionDomain;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.persistence-api-3.1.0.jar:jakarta/persistence/spi/ClassTransformer.class */
public interface ClassTransformer {
    byte[] transform(ClassLoader classLoader, String str, Class<?> cls, ProtectionDomain protectionDomain, byte[] bArr) throws TransformerException;
}