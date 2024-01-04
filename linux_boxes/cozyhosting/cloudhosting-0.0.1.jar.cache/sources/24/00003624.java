package org.hibernate.id.uuid;

import java.util.UUID;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/id/uuid/LocalObjectUuidHelper.class */
public class LocalObjectUuidHelper {
    private LocalObjectUuidHelper() {
    }

    public static String generateLocalObjectUuid() {
        return UUID.randomUUID().toString();
    }
}