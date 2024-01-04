package org.hibernate.type.descriptor.jdbc;

import java.util.concurrent.ConcurrentHashMap;
import org.hibernate.type.SqlTypes;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/type/descriptor/jdbc/JdbcTypeFamilyInformation.class */
public class JdbcTypeFamilyInformation {
    public static final JdbcTypeFamilyInformation INSTANCE = new JdbcTypeFamilyInformation();
    private ConcurrentHashMap<Integer, Family> typeCodeToFamilyMap = new ConcurrentHashMap<>();

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/type/descriptor/jdbc/JdbcTypeFamilyInformation$Family.class */
    public enum Family {
        BINARY(-2, -3, -4),
        NUMERIC(-5, 3, 8, 6, 4, 2, 7, 5, -6),
        CHARACTER(1, -16, -1, -15, -9, 12),
        DATETIME(91, 92, 93),
        CLOB(SqlTypes.CLOB, SqlTypes.NCLOB);
        
        private final int[] typeCodes;

        Family(int... typeCodes) {
            this.typeCodes = typeCodes;
            for (int typeCode : typeCodes) {
                JdbcTypeFamilyInformation.INSTANCE.typeCodeToFamilyMap.put(Integer.valueOf(typeCode), this);
            }
        }

        public int[] getTypeCodes() {
            return this.typeCodes;
        }
    }

    public Family locateJdbcTypeFamilyByTypeCode(int typeCode) {
        return this.typeCodeToFamilyMap.get(Integer.valueOf(typeCode));
    }
}