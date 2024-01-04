package org.aspectj.util;

import java.io.DataOutputStream;
import java.io.IOException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/util/TypeSafeEnum.class */
public class TypeSafeEnum {
    private final byte key;
    private final String name;

    public TypeSafeEnum(String name, int key) {
        this.name = name;
        if (key > 127 || key < -128) {
            throw new IllegalArgumentException("key doesn't fit into a byte: " + key);
        }
        this.key = (byte) key;
    }

    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }

    public byte getKey() {
        return this.key;
    }

    public void write(DataOutputStream s) throws IOException {
        s.writeByte(this.key);
    }

    public int hashCode() {
        return (this.name.hashCode() * 37) + this.key;
    }

    public boolean equals(Object o) {
        return (o instanceof TypeSafeEnum) && ((TypeSafeEnum) o).key == this.key && ((TypeSafeEnum) o).name.equals(this.name);
    }
}