package org.glassfish.jaxb.runtime.v2.runtime;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jaxb-runtime-4.0.1.jar:org/glassfish/jaxb/runtime/v2/runtime/AssociationMap.class */
public final class AssociationMap<XmlNode> {
    private final Map<XmlNode, Entry<XmlNode>> byElement = new IdentityHashMap();
    private final Map<Object, Entry<XmlNode>> byPeer = new IdentityHashMap();
    private final Set<XmlNode> usedNodes = new HashSet();

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jaxb-runtime-4.0.1.jar:org/glassfish/jaxb/runtime/v2/runtime/AssociationMap$Entry.class */
    public static final class Entry<XmlNode> {
        private XmlNode element;
        private Object inner;
        private Object outer;

        private Entry() {
        }

        public XmlNode element() {
            return this.element;
        }

        public Object inner() {
            return this.inner;
        }

        public Object outer() {
            return this.outer;
        }
    }

    public void addInner(XmlNode element, Object inner) {
        Entry<XmlNode> e = this.byElement.get(element);
        if (e != null) {
            if (((Entry) e).inner != null) {
                this.byPeer.remove(((Entry) e).inner);
            }
            ((Entry) e).inner = inner;
        } else {
            e = new Entry<>();
            ((Entry) e).element = element;
            ((Entry) e).inner = inner;
        }
        this.byElement.put(element, e);
        Entry<XmlNode> old = this.byPeer.put(inner, e);
        if (old != null) {
            if (((Entry) old).outer != null) {
                this.byPeer.remove(((Entry) old).outer);
            }
            if (((Entry) old).element != null) {
                this.byElement.remove(((Entry) old).element);
            }
        }
    }

    public void addOuter(XmlNode element, Object outer) {
        Entry<XmlNode> e = this.byElement.get(element);
        if (e != null) {
            if (((Entry) e).outer != null) {
                this.byPeer.remove(((Entry) e).outer);
            }
        } else {
            e = new Entry<>();
            ((Entry) e).element = element;
        }
        ((Entry) e).outer = outer;
        this.byElement.put(element, e);
        Entry<XmlNode> old = this.byPeer.put(outer, e);
        if (old != null) {
            ((Entry) old).outer = null;
            if (((Entry) old).inner == null) {
                this.byElement.remove(((Entry) old).element);
            }
        }
    }

    public void addUsed(XmlNode n) {
        this.usedNodes.add(n);
    }

    public Entry<XmlNode> byElement(Object e) {
        return this.byElement.get(e);
    }

    public Entry<XmlNode> byPeer(Object o) {
        return this.byPeer.get(o);
    }

    public Object getInnerPeer(XmlNode element) {
        Entry<XmlNode> e = byElement(element);
        if (e == null) {
            return null;
        }
        return ((Entry) e).inner;
    }

    public Object getOuterPeer(XmlNode element) {
        Entry<XmlNode> e = byElement(element);
        if (e == null) {
            return null;
        }
        return ((Entry) e).outer;
    }
}