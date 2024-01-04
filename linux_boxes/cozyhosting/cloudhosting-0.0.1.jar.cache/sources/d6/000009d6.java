package jakarta.activation;

import java.util.List;
import java.util.Map;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.activation-api-2.1.1.jar:jakarta/activation/MailcapRegistry.class */
public interface MailcapRegistry {
    Map<String, List<String>> getMailcapList(String str);

    Map<String, List<String>> getMailcapFallbackList(String str);

    String[] getMimeTypes();

    String[] getNativeCommands(String str);

    void appendToMailcap(String str);
}