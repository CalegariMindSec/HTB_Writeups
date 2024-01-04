package jakarta.activation;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.activation-api-2.1.1.jar:jakarta/activation/MimeTypeEntry.class */
public class MimeTypeEntry {
    private String type;
    private String extension;

    public MimeTypeEntry(String mime_type, String file_ext) {
        this.type = mime_type;
        this.extension = file_ext;
    }

    public String getMIMEType() {
        return this.type;
    }

    public String getFileExtension() {
        return this.extension;
    }

    public String toString() {
        return "MIMETypeEntry: " + this.type + ", " + this.extension;
    }
}