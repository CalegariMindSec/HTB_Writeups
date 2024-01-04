package org.hibernate.metamodel.mapping;

import java.util.List;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/metamodel/mapping/AssociationKey.class */
public class AssociationKey {
    private final String table;
    private final List<String> columns;
    private String str;

    public AssociationKey(String table, List<String> columns) {
        this.table = table;
        this.columns = columns;
    }

    public String getTable() {
        return this.table;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AssociationKey that = (AssociationKey) o;
        return this.table.equals(that.table) && this.columns.equals(that.columns);
    }

    public int hashCode() {
        return this.table.hashCode();
    }

    public String toString() {
        if (this.str == null) {
            this.str = "AssociationKey(table=" + this.table + ", columns={" + String.join(",", this.columns) + "})";
        }
        return this.str;
    }
}