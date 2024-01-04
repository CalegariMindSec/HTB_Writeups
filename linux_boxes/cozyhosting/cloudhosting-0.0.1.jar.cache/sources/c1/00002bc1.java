package org.hibernate.boot.archive.spi;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/archive/spi/InputStreamAccess.class */
public interface InputStreamAccess {
    String getStreamName();

    InputStream accessInputStream();

    default <X> X fromStream(Function<InputStream, X> action) {
        InputStream inputStream = accessInputStream();
        try {
            return action.apply(inputStream);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
            }
        }
    }
}