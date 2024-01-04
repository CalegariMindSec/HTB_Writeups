package com.sun.xml.txw2;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/txw2-4.0.1.jar:com/sun/xml/txw2/Attribute.class */
final class Attribute {
    final String nsUri;
    final String localName;
    Attribute next;
    final StringBuilder value = new StringBuilder();
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !Attribute.class.desiredAssertionStatus();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Attribute(String nsUri, String localName) {
        if (!$assertionsDisabled && (nsUri == null || localName == null)) {
            throw new AssertionError();
        }
        this.nsUri = nsUri;
        this.localName = localName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasName(String nsUri, String localName) {
        return this.localName.equals(localName) && this.nsUri.equals(nsUri);
    }
}