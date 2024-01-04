package org.postgresql;

import java.util.HashMap;
import java.util.Map;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/PGEnvironment.class */
public enum PGEnvironment {
    ORG_POSTGRESQL_PGPASSFILE("org.postgresql.pgpassfile", null, "Specified location of password file."),
    PGPASSFILE("PGPASSFILE", "pgpass", "Specified location of password file."),
    ORG_POSTGRESQL_PGSERVICEFILE("org.postgresql.pgservicefile", null, "Specifies the service resource to resolve connection properties."),
    PGSERVICEFILE("PGSERVICEFILE", "pg_service.conf", "Specifies the service resource to resolve connection properties."),
    PGSYSCONFDIR("PGSYSCONFDIR", null, "Specifies the directory containing the PGSERVICEFILE file");
    
    private final String name;
    private final String defaultValue;
    private final String description;
    private static final Map<String, PGEnvironment> PROPS_BY_NAME = new HashMap();

    static {
        PGEnvironment[] values;
        for (PGEnvironment prop : values()) {
            if (PROPS_BY_NAME.put(prop.getName(), prop) != null) {
                throw new IllegalStateException("Duplicate PGProperty name: " + prop.getName());
            }
        }
    }

    PGEnvironment(String name, String defaultValue, String description) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public String getDescription() {
        return this.description;
    }
}