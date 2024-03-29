package com.sun.xml.txw2;

import java.util.EmptyStackException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.thymeleaf.engine.XMLDeclaration;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/txw2-4.0.1.jar:com/sun/xml/txw2/NamespaceSupport.class */
public final class NamespaceSupport {
    public static final String XMLNS = "http://www.w3.org/XML/1998/namespace";
    public static final String NSDECL = "http://www.w3.org/xmlns/2000/";
    private static final Enumeration<String> EMPTY_ENUMERATION = new Vector().elements();
    private Context[] contexts;
    private Context currentContext;
    private int contextPos;
    private boolean namespaceDeclUris;

    public NamespaceSupport() {
        reset();
    }

    public void reset() {
        this.contexts = new Context[32];
        this.namespaceDeclUris = false;
        this.contextPos = 0;
        Context[] contextArr = this.contexts;
        int i = this.contextPos;
        Context context = new Context();
        this.currentContext = context;
        contextArr[i] = context;
        this.currentContext.declarePrefix(XMLDeclaration.DEFAULT_KEYWORD, XMLNS);
    }

    public void pushContext() {
        int max = this.contexts.length;
        this.contextPos++;
        if (this.contextPos >= max) {
            Context[] newContexts = new Context[max * 2];
            System.arraycopy(this.contexts, 0, newContexts, 0, max);
            int i = max * 2;
            this.contexts = newContexts;
        }
        this.currentContext = this.contexts[this.contextPos];
        if (this.currentContext == null) {
            Context[] contextArr = this.contexts;
            int i2 = this.contextPos;
            Context context = new Context();
            this.currentContext = context;
            contextArr[i2] = context;
        }
        if (this.contextPos > 0) {
            this.currentContext.setParent(this.contexts[this.contextPos - 1]);
        }
    }

    public void popContext() {
        this.contexts[this.contextPos].clear();
        this.contextPos--;
        if (this.contextPos < 0) {
            throw new EmptyStackException();
        }
        this.currentContext = this.contexts[this.contextPos];
    }

    public boolean declarePrefix(String prefix, String uri) {
        if (prefix.equals(XMLDeclaration.DEFAULT_KEYWORD) || prefix.equals("xmlns")) {
            return false;
        }
        this.currentContext.declarePrefix(prefix, uri);
        return true;
    }

    public String[] processName(String qName, String[] parts, boolean isAttribute) {
        String[] myParts = this.currentContext.processName(qName, isAttribute);
        if (myParts == null) {
            return null;
        }
        parts[0] = myParts[0];
        parts[1] = myParts[1];
        parts[2] = myParts[2];
        return parts;
    }

    public String getURI(String prefix) {
        return this.currentContext.getURI(prefix);
    }

    public Enumeration<String> getPrefixes() {
        return this.currentContext.getPrefixes();
    }

    public String getPrefix(String uri) {
        return this.currentContext.getPrefix(uri);
    }

    public Enumeration<String> getPrefixes(String uri) {
        Vector<String> prefixes = new Vector<>();
        Enumeration<String> allPrefixes = getPrefixes();
        while (allPrefixes.hasMoreElements()) {
            String prefix = allPrefixes.nextElement();
            if (uri.equals(getURI(prefix))) {
                prefixes.addElement(prefix);
            }
        }
        return prefixes.elements();
    }

    public Enumeration<String> getDeclaredPrefixes() {
        return this.currentContext.getDeclaredPrefixes();
    }

    public void setNamespaceDeclUris(boolean value) {
        if (this.contextPos != 0) {
            throw new IllegalStateException();
        }
        if (value == this.namespaceDeclUris) {
            return;
        }
        this.namespaceDeclUris = value;
        if (value) {
            this.currentContext.declarePrefix("xmlns", NSDECL);
            return;
        }
        Context[] contextArr = this.contexts;
        int i = this.contextPos;
        Context context = new Context();
        this.currentContext = context;
        contextArr[i] = context;
        this.currentContext.declarePrefix(XMLDeclaration.DEFAULT_KEYWORD, XMLNS);
    }

    public boolean isNamespaceDeclUris() {
        return this.namespaceDeclUris;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/txw2-4.0.1.jar:com/sun/xml/txw2/NamespaceSupport$Context.class */
    public final class Context {
        Hashtable<String, String> prefixTable;
        Hashtable<String, String> uriTable;
        Hashtable<String, String[]> elementNameTable;
        Hashtable<String, String[]> attributeNameTable;
        String defaultNS = "";
        private Vector<String> declarations = null;
        private boolean declSeen = false;
        private Context parent = null;

        Context() {
            copyTables();
        }

        void setParent(Context parent) {
            this.parent = parent;
            this.declarations = null;
            this.prefixTable = parent.prefixTable;
            this.uriTable = parent.uriTable;
            this.elementNameTable = parent.elementNameTable;
            this.attributeNameTable = parent.attributeNameTable;
            this.defaultNS = parent.defaultNS;
            this.declSeen = false;
        }

        void clear() {
            this.parent = null;
            this.prefixTable = null;
            this.uriTable = null;
            this.elementNameTable = null;
            this.attributeNameTable = null;
            this.defaultNS = "";
        }

        void declarePrefix(String prefix, String uri) {
            if (!this.declSeen) {
                copyTables();
            }
            if (this.declarations == null) {
                this.declarations = new Vector<>();
            }
            String prefix2 = prefix.intern();
            String uri2 = uri.intern();
            if ("".equals(prefix2)) {
                this.defaultNS = uri2;
            } else {
                this.prefixTable.put(prefix2, uri2);
                this.uriTable.put(uri2, prefix2);
            }
            this.declarations.addElement(prefix2);
        }

        String[] processName(String qName, boolean isAttribute) {
            Hashtable<String, String[]> table;
            String uri;
            if (isAttribute) {
                table = this.attributeNameTable;
            } else {
                table = this.elementNameTable;
            }
            String[] name = table.get(qName);
            if (name != null) {
                return name;
            }
            String[] name2 = new String[3];
            name2[2] = qName.intern();
            int index = qName.indexOf(58);
            if (index == -1) {
                if (isAttribute) {
                    if (qName == "xmlns" && NamespaceSupport.this.namespaceDeclUris) {
                        name2[0] = NamespaceSupport.NSDECL;
                    } else {
                        name2[0] = "";
                    }
                } else {
                    name2[0] = this.defaultNS;
                }
                name2[1] = name2[2];
            } else {
                String prefix = qName.substring(0, index);
                String local = qName.substring(index + 1);
                if ("".equals(prefix)) {
                    uri = this.defaultNS;
                } else {
                    uri = this.prefixTable.get(prefix);
                }
                if (uri != null) {
                    if (!isAttribute && "xmlns".equals(prefix)) {
                        return null;
                    }
                    name2[0] = uri;
                    name2[1] = local.intern();
                } else {
                    return null;
                }
            }
            table.put(name2[2], name2);
            return name2;
        }

        String getURI(String prefix) {
            if ("".equals(prefix)) {
                return this.defaultNS;
            }
            if (this.prefixTable == null) {
                return null;
            }
            return this.prefixTable.get(prefix);
        }

        String getPrefix(String uri) {
            String uriPrefix;
            if (this.uriTable == null || (uriPrefix = this.uriTable.get(uri)) == null) {
                return null;
            }
            String verifyNamespace = this.prefixTable.get(uriPrefix);
            if (uri.equals(verifyNamespace)) {
                return uriPrefix;
            }
            return null;
        }

        Enumeration<String> getDeclaredPrefixes() {
            if (this.declarations == null) {
                return NamespaceSupport.EMPTY_ENUMERATION;
            }
            return this.declarations.elements();
        }

        Enumeration<String> getPrefixes() {
            if (this.prefixTable == null) {
                return NamespaceSupport.EMPTY_ENUMERATION;
            }
            return this.prefixTable.keys();
        }

        private void copyTables() {
            if (this.prefixTable != null) {
                this.prefixTable = (Hashtable) this.prefixTable.clone();
            } else {
                this.prefixTable = new Hashtable<>();
            }
            if (this.uriTable != null) {
                this.uriTable = (Hashtable) this.uriTable.clone();
            } else {
                this.uriTable = new Hashtable<>();
            }
            this.elementNameTable = new Hashtable<>();
            this.attributeNameTable = new Hashtable<>();
            this.declSeen = true;
        }
    }
}