package org.glassfish.jaxb.runtime.v2.runtime;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jaxb-runtime-4.0.1.jar:org/glassfish/jaxb/runtime/v2/runtime/NameList.class */
public final class NameList {
    public final String[] namespaceURIs;
    public final boolean[] nsUriCannotBeDefaulted;
    public final String[] localNames;
    public final int numberOfElementNames;
    public final int numberOfAttributeNames;

    public NameList(String[] namespaceURIs, boolean[] nsUriCannotBeDefaulted, String[] localNames, int numberElementNames, int numberAttributeNames) {
        this.namespaceURIs = namespaceURIs;
        this.nsUriCannotBeDefaulted = nsUriCannotBeDefaulted;
        this.localNames = localNames;
        this.numberOfElementNames = numberElementNames;
        this.numberOfAttributeNames = numberAttributeNames;
    }
}