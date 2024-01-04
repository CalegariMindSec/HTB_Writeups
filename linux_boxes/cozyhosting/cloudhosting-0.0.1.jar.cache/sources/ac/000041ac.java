package org.jboss.jandex;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jandex-2.4.2.Final.jar:org/jboss/jandex/DotName.class */
public final class DotName implements Comparable<DotName> {
    static final DotName JAVA_NAME = new DotName(null, "java", true, false);
    static final DotName JAVA_LANG_NAME = new DotName(JAVA_NAME, "lang", true, false);
    static final DotName OBJECT_NAME = new DotName(JAVA_LANG_NAME, "Object", true, false);
    static final DotName ENUM_NAME = new DotName(JAVA_LANG_NAME, "Enum", true, false);
    static final DotName RECORD_NAME = new DotName(JAVA_LANG_NAME, "Record", true, false);
    private final DotName prefix;
    private final String local;
    private int hash;
    private final boolean componentized;
    private final boolean innerClass;

    public static DotName createSimple(String name) {
        return new DotName(null, name, false, false);
    }

    public static DotName createComponentized(DotName prefix, String localName) {
        if (localName.indexOf(46) != -1) {
            throw new IllegalArgumentException("A componentized DotName can not contain '.' characters in a local name");
        }
        return new DotName(prefix, localName, true, false);
    }

    public static DotName createComponentized(DotName prefix, String localName, boolean innerClass) {
        if (localName.indexOf(46) != -1) {
            throw new IllegalArgumentException("A componentized DotName can not contain '.' characters in a local name");
        }
        return new DotName(prefix, localName, true, innerClass);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DotName(DotName prefix, String local, boolean noDots, boolean innerClass) {
        if (local == null) {
            throw new IllegalArgumentException("Local string can not be null");
        }
        if (prefix != null && !prefix.componentized) {
            throw new IllegalArgumentException("A componentized DotName must have a componentized prefix, or null");
        }
        this.prefix = prefix;
        this.local = local;
        this.componentized = noDots;
        this.innerClass = innerClass;
    }

    public DotName prefix() {
        return this.prefix;
    }

    public String local() {
        return this.local;
    }

    public String withoutPackagePrefix() {
        if (this.componentized) {
            StringBuilder builder = new StringBuilder();
            stripPackage(builder);
            return builder.toString();
        }
        int index = this.local.lastIndexOf(46);
        return index == -1 ? this.local : index < this.local.length() - 1 ? this.local.substring(index + 1) : "";
    }

    private void stripPackage(StringBuilder builder) {
        if (this.innerClass) {
            this.prefix.stripPackage(builder);
            builder.append('$');
        }
        builder.append(this.local);
    }

    public String packagePrefix() {
        if (this.componentized) {
            if (this.innerClass) {
                return this.prefix.packagePrefix();
            }
            return this.prefix.toString();
        }
        int index = this.local.lastIndexOf(46);
        if (index == -1) {
            return null;
        }
        return this.local.substring(0, index);
    }

    public boolean isComponentized() {
        return this.componentized;
    }

    public boolean isInner() {
        return this.innerClass;
    }

    public String toString() {
        return toString('.');
    }

    public String toString(char delim) {
        String string = this.local;
        if (this.prefix != null) {
            StringBuilder builder = new StringBuilder();
            buildString(delim, builder);
            string = builder.toString();
        }
        return string;
    }

    private void buildString(char delim, StringBuilder builder) {
        if (this.prefix != null) {
            this.prefix.buildString(delim, builder);
            builder.append(this.innerClass ? '$' : delim);
        }
        builder.append(this.local);
    }

    public int hashCode() {
        int hash;
        int hash2 = this.hash;
        if (hash2 != 0) {
            return hash2;
        }
        if (this.prefix != null) {
            hash = (this.prefix.hashCode() * 31) + (this.innerClass ? 36 : 46);
            for (int i = 0; i < this.local.length(); i++) {
                hash = (31 * hash) + this.local.charAt(i);
            }
        } else {
            hash = this.local.hashCode();
        }
        int i2 = hash;
        this.hash = i2;
        return i2;
    }

    @Override // java.lang.Comparable
    public int compareTo(DotName other) {
        int c1;
        int c2;
        IndexState s1 = new IndexState();
        IndexState s2 = new IndexState();
        do {
            c1 = nextChar(s1, this);
            c2 = nextChar(s2, other);
            if (c1 == -1) {
                return c2 == -1 ? 0 : -1;
            } else if (c2 == -1) {
                return 1;
            }
        } while (c1 == c2);
        return c1 - c2;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DotName)) {
            return false;
        }
        DotName other = (DotName) o;
        if (other.prefix == null && this.prefix == null) {
            return this.local.equals(other.local) && this.innerClass == other.innerClass;
        } else if (!other.componentized && this.componentized) {
            return crossEquals(other, this);
        } else {
            if (!other.componentized || this.componentized) {
                return this.prefix != null && this.innerClass == other.innerClass && this.local.equals(other.local) && this.prefix.equals(other.prefix);
            }
            return crossEquals(this, other);
        }
    }

    private static boolean crossEquals(DotName simple, DotName comp) {
        String exactToMatch = simple.local;
        int cursor = 0;
        int len = exactToMatch.length();
        DotName dotName = comp;
        while (true) {
            DotName d = dotName;
            if (d == null || cursor - 1 > len) {
                break;
            }
            cursor += 1 + d.local.length();
            dotName = d.prefix;
        }
        int cursor2 = cursor - 1;
        if (cursor2 != len) {
            return false;
        }
        DotName dotName2 = comp;
        while (true) {
            DotName current = dotName2;
            if (current == null) {
                return cursor2 == -1;
            }
            String nextFragment = current.local;
            int fragLength = nextFragment.length();
            if (!exactToMatch.regionMatches(cursor2 - fragLength, nextFragment, 0, fragLength)) {
                return false;
            }
            cursor2 = (cursor2 - fragLength) - 1;
            if (cursor2 == -1) {
                return current.prefix == null;
            }
            char expectNext = current.innerClass ? '$' : '.';
            if (exactToMatch.charAt(cursor2) != expectNext) {
                return false;
            }
            dotName2 = current.prefix;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jandex-2.4.2.Final.jar:org/jboss/jandex/DotName$IndexState.class */
    public static class IndexState {
        DotName currentPrefix;
        int offset;

        private IndexState() {
        }
    }

    private int nextChar(IndexState state, DotName name) {
        DotName n;
        if (state.offset == -1) {
            return -1;
        }
        if (!name.componentized) {
            if (state.offset > name.local.length() - 1) {
                state.offset = -1;
                return -1;
            }
            String str = name.local;
            int i = state.offset;
            state.offset = i + 1;
            return str.charAt(i);
        }
        DotName p = name;
        DotName dotName = name;
        while (true) {
            n = dotName;
            if (n.prefix == state.currentPrefix) {
                break;
            }
            p = n;
            dotName = n.prefix;
        }
        if (state.offset > n.local.length() - 1) {
            if (n == name) {
                state.offset = -1;
                return -1;
            }
            state.offset = 0;
            state.currentPrefix = n;
            return p.isInner() ? 36 : 46;
        }
        String str2 = n.local;
        int i2 = state.offset;
        state.offset = i2 + 1;
        return str2.charAt(i2);
    }
}