package org.postgresql;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/PGNotification.class */
public interface PGNotification {
    String getName();

    int getPID();

    String getParameter();
}