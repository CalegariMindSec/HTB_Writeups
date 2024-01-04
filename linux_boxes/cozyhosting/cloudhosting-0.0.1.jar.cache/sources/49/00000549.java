package com.fasterxml.jackson.databind.jsonFormatVisitors;

import com.fasterxml.jackson.annotation.JsonValue;
import org.thymeleaf.spring6.processor.SpringInputGeneralFieldTagProcessor;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jackson-databind-2.14.1.jar:com/fasterxml/jackson/databind/jsonFormatVisitors/JsonValueFormat.class */
public enum JsonValueFormat {
    COLOR(SpringInputGeneralFieldTagProcessor.COLOR_INPUT_TYPE_ATTR_VALUE),
    DATE(SpringInputGeneralFieldTagProcessor.DATE_INPUT_TYPE_ATTR_VALUE),
    DATE_TIME("date-time"),
    EMAIL(SpringInputGeneralFieldTagProcessor.EMAIL_INPUT_TYPE_ATTR_VALUE),
    HOST_NAME("host-name"),
    IP_ADDRESS("ip-address"),
    IPV6("ipv6"),
    PHONE("phone"),
    REGEX("regex"),
    STYLE("style"),
    TIME(SpringInputGeneralFieldTagProcessor.TIME_INPUT_TYPE_ATTR_VALUE),
    URI("uri"),
    UTC_MILLISEC("utc-millisec"),
    UUID("uuid");
    
    private final String _desc;

    JsonValueFormat(String desc) {
        this._desc = desc;
    }

    @Override // java.lang.Enum
    @JsonValue
    public String toString() {
        return this._desc;
    }
}