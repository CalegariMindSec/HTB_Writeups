package org.postgresql.core.v3;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/core/v3/TypeTransferModeRegistry.class */
public interface TypeTransferModeRegistry {
    boolean useBinaryForSend(int i);

    boolean useBinaryForReceive(int i);
}