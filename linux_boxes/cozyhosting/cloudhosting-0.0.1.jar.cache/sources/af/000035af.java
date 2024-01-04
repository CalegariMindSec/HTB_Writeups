package org.hibernate.graph;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/graph/GraphNode.class */
public interface GraphNode<J> {
    boolean isMutable();

    GraphNode<J> makeCopy(boolean z);
}