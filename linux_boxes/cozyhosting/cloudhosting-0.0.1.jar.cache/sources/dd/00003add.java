package org.hibernate.query.spi;

import java.util.Iterator;
import org.hibernate.Incubating;

@Incubating
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/query/spi/CloseableIterator.class */
public interface CloseableIterator<T> extends Iterator<T>, AutoCloseable {
    void close();
}