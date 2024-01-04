package io.micrometer.core.instrument.util;

import io.micrometer.common.lang.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/micrometer-core-1.10.3.jar:io/micrometer/core/instrument/util/IOUtils.class */
public final class IOUtils {
    private static final int EOF = -1;
    private static final int DEFAULT_BUFFER_SIZE = 1024;

    public static String toString(@Nullable InputStream inputStream, Charset charset) {
        if (inputStream == null) {
            return "";
        }
        try {
            StringWriter writer = new StringWriter();
            InputStreamReader reader = new InputStreamReader(inputStream, charset);
            try {
                BufferedReader bufferedReader = new BufferedReader(reader);
                try {
                    char[] chars = new char[1024];
                    while (true) {
                        int readChars = bufferedReader.read(chars);
                        if (readChars != -1) {
                            writer.write(chars, 0, readChars);
                        } else {
                            String stringWriter = writer.toString();
                            bufferedReader.close();
                            reader.close();
                            writer.close();
                            return stringWriter;
                        }
                    }
                } catch (Throwable th) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                try {
                    reader.close();
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                }
                throw th3;
            }
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    public static String toString(@Nullable InputStream inputStream) {
        return toString(inputStream, Charset.defaultCharset());
    }

    private IOUtils() {
    }
}