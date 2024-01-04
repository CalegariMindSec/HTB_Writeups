package jakarta.activation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.activation-api-2.1.1.jar:jakarta/activation/DataSource.class */
public interface DataSource {
    InputStream getInputStream() throws IOException;

    OutputStream getOutputStream() throws IOException;

    String getContentType();

    String getName();
}