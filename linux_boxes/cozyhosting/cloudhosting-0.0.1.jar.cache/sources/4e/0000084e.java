package io.micrometer.core.instrument.binder.httpcomponents;

import java.util.function.Function;
import org.apache.http.Header;
import org.apache.http.HttpRequest;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/micrometer-core-1.10.3.jar:io/micrometer/core/instrument/binder/httpcomponents/DefaultUriMapper.class */
public class DefaultUriMapper implements Function<HttpRequest, String> {
    public static final String URI_PATTERN_HEADER = "URI_PATTERN";

    @Override // java.util.function.Function
    public String apply(HttpRequest httpRequest) {
        Header uriPattern = httpRequest.getLastHeader("URI_PATTERN");
        if (uriPattern != null && uriPattern.getValue() != null) {
            return uriPattern.getValue();
        }
        return "UNKNOWN";
    }
}