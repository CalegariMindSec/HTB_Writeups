package org.hibernate.query;

import java.util.List;
import org.hibernate.Incubating;

@FunctionalInterface
@Incubating
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/query/ResultListTransformer.class */
public interface ResultListTransformer<T> {
    List<T> transformList(List<T> list);
}