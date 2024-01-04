package org.hibernate.cfg;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/cfg/UniqueConstraintHolder.class */
public class UniqueConstraintHolder {
    private String name;
    private String[] columns;

    public String getName() {
        return this.name;
    }

    public UniqueConstraintHolder setName(String name) {
        this.name = name;
        return this;
    }

    public String[] getColumns() {
        return this.columns;
    }

    public UniqueConstraintHolder setColumns(String[] columns) {
        this.columns = columns;
        return this;
    }
}