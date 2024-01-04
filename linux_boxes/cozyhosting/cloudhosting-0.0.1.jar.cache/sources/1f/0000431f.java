package org.postgresql.hostchooser;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/hostchooser/HostStatus.class */
public enum HostStatus {
    ConnectFail,
    ConnectOK,
    Primary,
    Secondary
}