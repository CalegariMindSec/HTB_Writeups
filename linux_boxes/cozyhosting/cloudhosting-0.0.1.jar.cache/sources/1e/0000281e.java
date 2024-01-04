package org.glassfish.jaxb.runtime.marshaller;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jaxb-runtime-4.0.1.jar:org/glassfish/jaxb/runtime/marshaller/NamespacePrefixMapper.class */
public abstract class NamespacePrefixMapper {
    private static final String[] EMPTY_STRING = new String[0];

    public abstract String getPreferredPrefix(String str, String str2, boolean z);

    public String[] getPreDeclaredNamespaceUris() {
        return EMPTY_STRING;
    }

    public String[] getPreDeclaredNamespaceUris2() {
        return EMPTY_STRING;
    }

    public String[] getContextualNamespaceDecls() {
        return EMPTY_STRING;
    }
}