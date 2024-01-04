package org.postgresql.plugin;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/plugin/AuthenticationRequestType.class */
public enum AuthenticationRequestType {
    CLEARTEXT_PASSWORD,
    GSS,
    MD5_PASSWORD,
    SASL
}