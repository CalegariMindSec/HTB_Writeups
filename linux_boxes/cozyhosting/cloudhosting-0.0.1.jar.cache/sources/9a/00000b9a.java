package jakarta.websocket;

import org.postgresql.core.Oid;
import org.thymeleaf.spring6.processor.SpringValueTagProcessor;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-websocket-10.1.5.jar:jakarta/websocket/CloseReason.class */
public class CloseReason {
    private final CloseCode closeCode;
    private final String reasonPhrase;

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-websocket-10.1.5.jar:jakarta/websocket/CloseReason$CloseCode.class */
    public interface CloseCode {
        int getCode();
    }

    public CloseReason(CloseCode closeCode, String reasonPhrase) {
        this.closeCode = closeCode;
        this.reasonPhrase = reasonPhrase;
    }

    public CloseCode getCloseCode() {
        return this.closeCode;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    public String toString() {
        return "CloseReason: code [" + this.closeCode.getCode() + "], reason [" + this.reasonPhrase + "]";
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/tomcat-embed-websocket-10.1.5.jar:jakarta/websocket/CloseReason$CloseCodes.class */
    public enum CloseCodes implements CloseCode {
        NORMAL_CLOSURE(1000),
        GOING_AWAY(Oid.BYTEA_ARRAY),
        PROTOCOL_ERROR(Oid.CHAR_ARRAY),
        CANNOT_ACCEPT(Oid.NAME_ARRAY),
        RESERVED(1004),
        NO_STATUS_CODE(1005),
        CLOSED_ABNORMALLY(1006),
        NOT_CONSISTENT(Oid.INT4_ARRAY),
        VIOLATED_POLICY(1008),
        TOO_BIG(Oid.TEXT_ARRAY),
        NO_EXTENSION(SpringValueTagProcessor.ATTR_PRECEDENCE),
        UNEXPECTED_CONDITION(1011),
        SERVICE_RESTART(1012),
        TRY_AGAIN_LATER(1013),
        TLS_HANDSHAKE_FAILURE(Oid.VARCHAR_ARRAY);
        
        private int code;

        CloseCodes(int code) {
            this.code = code;
        }

        public static CloseCode getCloseCode(final int code) {
            if (code > 2999 && code < 5000) {
                return new CloseCode() { // from class: jakarta.websocket.CloseReason.CloseCodes.1
                    @Override // jakarta.websocket.CloseReason.CloseCode
                    public int getCode() {
                        return code;
                    }
                };
            }
            switch (code) {
                case 1000:
                    return NORMAL_CLOSURE;
                case Oid.BYTEA_ARRAY /* 1001 */:
                    return GOING_AWAY;
                case Oid.CHAR_ARRAY /* 1002 */:
                    return PROTOCOL_ERROR;
                case Oid.NAME_ARRAY /* 1003 */:
                    return CANNOT_ACCEPT;
                case 1004:
                    return RESERVED;
                case 1005:
                    return NO_STATUS_CODE;
                case 1006:
                    return CLOSED_ABNORMALLY;
                case Oid.INT4_ARRAY /* 1007 */:
                    return NOT_CONSISTENT;
                case 1008:
                    return VIOLATED_POLICY;
                case Oid.TEXT_ARRAY /* 1009 */:
                    return TOO_BIG;
                case SpringValueTagProcessor.ATTR_PRECEDENCE /* 1010 */:
                    return NO_EXTENSION;
                case 1011:
                    return UNEXPECTED_CONDITION;
                case 1012:
                    return SERVICE_RESTART;
                case 1013:
                    return TRY_AGAIN_LATER;
                case Oid.BPCHAR_ARRAY /* 1014 */:
                default:
                    throw new IllegalArgumentException("Invalid close code: [" + code + "]");
                case Oid.VARCHAR_ARRAY /* 1015 */:
                    return TLS_HANDSHAKE_FAILURE;
            }
        }

        @Override // jakarta.websocket.CloseReason.CloseCode
        public int getCode() {
            return this.code;
        }
    }
}