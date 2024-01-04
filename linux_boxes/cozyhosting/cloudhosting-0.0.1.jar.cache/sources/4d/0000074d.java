package com.zaxxer.hikari;

import java.sql.SQLException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/HikariCP-5.0.1.jar:com/zaxxer/hikari/SQLExceptionOverride.class */
public interface SQLExceptionOverride {

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/HikariCP-5.0.1.jar:com/zaxxer/hikari/SQLExceptionOverride$Override.class */
    public enum Override {
        CONTINUE_EVICT,
        DO_NOT_EVICT
    }

    default Override adjudicate(SQLException sqlException) {
        return Override.CONTINUE_EVICT;
    }
}