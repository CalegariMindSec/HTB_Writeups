package org.hibernate.resource.jdbc.spi;

import java.io.Serializable;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/resource/jdbc/spi/StatementInspector.class */
public interface StatementInspector extends Serializable {
    String inspect(String str);
}