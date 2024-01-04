package com.sun.xml.txw2;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/txw2-4.0.1.jar:com/sun/xml/txw2/NamespaceDecl.class */
final class NamespaceDecl {
    final String uri;
    boolean requirePrefix;
    final String dummyPrefix;
    final char uniqueId;
    String prefix;
    boolean declared;
    NamespaceDecl next;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NamespaceDecl(char uniqueId, String uri, String prefix, boolean requirePrefix) {
        this.dummyPrefix = new StringBuilder(2).append((char) 0).append(uniqueId).toString();
        this.uri = uri;
        this.prefix = prefix;
        this.requirePrefix = requirePrefix;
        this.uniqueId = uniqueId;
    }
}