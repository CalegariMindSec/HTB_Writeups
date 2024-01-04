package io.micrometer.core.ipc.http;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/micrometer-core-1.10.3.jar:io/micrometer/core/ipc/http/HttpStatusClass.class */
enum HttpStatusClass {
    INFORMATIONAL(100, 200),
    SUCCESS(200, 300),
    REDIRECTION(300, 400),
    CLIENT_ERROR(400, 500),
    SERVER_ERROR(500, 600),
    UNKNOWN(0, 0) { // from class: io.micrometer.core.ipc.http.HttpStatusClass.1
        @Override // io.micrometer.core.ipc.http.HttpStatusClass
        public boolean contains(int code) {
            return code < 100 || code >= 600;
        }
    };
    
    private final int min;
    private final int max;

    public static HttpStatusClass valueOf(int code) {
        if (INFORMATIONAL.contains(code)) {
            return INFORMATIONAL;
        }
        if (SUCCESS.contains(code)) {
            return SUCCESS;
        }
        if (REDIRECTION.contains(code)) {
            return REDIRECTION;
        }
        if (CLIENT_ERROR.contains(code)) {
            return CLIENT_ERROR;
        }
        if (SERVER_ERROR.contains(code)) {
            return SERVER_ERROR;
        }
        return UNKNOWN;
    }

    HttpStatusClass(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public boolean contains(int code) {
        return code >= this.min && code < this.max;
    }
}