package lombok.core.debug;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.unbescape.uri.UriEscape;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:lombok/core/debug/FileLog.SCL.lombok */
public class FileLog {
    private static FileOutputStream fos;

    public static void log(String message) {
        log(message, null);
    }

    public static synchronized void log(String message, Throwable t) {
        try {
            if (fos == null) {
                fos = new FileOutputStream(new File(System.getProperty("user.home"), "LOMBOK-DEBUG-OUT.txt"));
                Runtime.getRuntime().addShutdownHook(new Thread() { // from class: lombok.core.debug.FileLog.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        try {
                            FileLog.fos.close();
                        } catch (Throwable unused) {
                        }
                    }
                });
            }
            fos.write(message.getBytes(UriEscape.DEFAULT_ENCODING));
            fos.write(10);
            if (t != null) {
                StringWriter sw = new StringWriter();
                t.printStackTrace(new PrintWriter(sw));
                fos.write(sw.toString().getBytes(UriEscape.DEFAULT_ENCODING));
                fos.write(10);
            }
            fos.flush();
        } catch (IOException e) {
            throw new IllegalStateException("Internal lombok file-based debugging not possible", e);
        }
    }
}