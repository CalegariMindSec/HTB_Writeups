package jakarta.xml.bind;

import java.io.IOException;
import javax.xml.transform.Result;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.xml.bind-api-4.0.0.jar:jakarta/xml/bind/SchemaOutputResolver.class */
public abstract class SchemaOutputResolver {
    public abstract Result createOutput(String str, String str2) throws IOException;
}