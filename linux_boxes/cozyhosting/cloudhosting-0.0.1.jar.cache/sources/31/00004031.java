package org.hibernate.type;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/type/SqlTypes.class */
public class SqlTypes {
    public static final int BIT = -7;
    public static final int TINYINT = -6;
    public static final int SMALLINT = 5;
    public static final int INTEGER = 4;
    public static final int BIGINT = -5;
    public static final int FLOAT = 6;
    public static final int REAL = 7;
    public static final int DOUBLE = 8;
    public static final int NUMERIC = 2;
    public static final int DECIMAL = 3;
    public static final int CHAR = 1;
    public static final int VARCHAR = 12;
    public static final int LONGVARCHAR = -1;
    public static final int LONG32VARCHAR = 4001;
    public static final int DATE = 91;
    public static final int TIME = 92;
    public static final int TIMESTAMP = 93;
    public static final int BINARY = -2;
    public static final int VARBINARY = -3;
    public static final int LONGVARBINARY = -4;
    public static final int LONG32VARBINARY = 4003;
    public static final int NULL = 0;
    public static final int OTHER = 1111;
    public static final int JAVA_OBJECT = 2000;
    public static final int DISTINCT = 2001;
    public static final int STRUCT = 2002;
    public static final int ARRAY = 2003;
    public static final int BLOB = 2004;
    public static final int CLOB = 2005;
    public static final int REF = 2006;
    public static final int DATALINK = 70;
    public static final int BOOLEAN = 16;
    public static final int ROWID = -8;
    public static final int NCHAR = -15;
    public static final int NVARCHAR = -9;
    public static final int LONGNVARCHAR = -16;
    public static final int LONG32NVARCHAR = 4002;
    public static final int NCLOB = 2011;
    public static final int SQLXML = 2009;
    public static final int REF_CURSOR = 2012;
    public static final int TIME_WITH_TIMEZONE = 2013;
    public static final int TIMESTAMP_WITH_TIMEZONE = 2014;
    public static final int UUID = 3000;
    public static final int JSON = 3001;
    public static final int INET = 3002;
    public static final int TIMESTAMP_UTC = 3003;
    public static final int INTERVAL_SECOND = 3100;
    public static final int GEOMETRY = 3200;
    public static final int POINT = 3201;
    public static final int GEOGRAPHY = 3250;

    private SqlTypes() {
    }

    public static boolean isNumericType(int sqlType) {
        switch (sqlType) {
            case BIT /* -7 */:
            case TINYINT /* -6 */:
            case BIGINT /* -5 */:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return true;
            case LONGVARBINARY /* -4 */:
            case -3:
            case -2:
            case -1:
            case 0:
            case 1:
            default:
                return false;
        }
    }

    public static boolean isCharacterOrClobType(int sqlType) {
        switch (sqlType) {
            case LONGNVARCHAR /* -16 */:
            case NCHAR /* -15 */:
            case NVARCHAR /* -9 */:
            case -1:
            case 1:
            case 12:
            case CLOB /* 2005 */:
            case NCLOB /* 2011 */:
                return true;
            default:
                return false;
        }
    }

    public static boolean isCharacterType(int sqlType) {
        switch (sqlType) {
            case LONGNVARCHAR /* -16 */:
            case NCHAR /* -15 */:
            case NVARCHAR /* -9 */:
            case -1:
            case 1:
            case 12:
                return true;
            default:
                return false;
        }
    }

    public static boolean isVarcharType(int sqlType) {
        switch (sqlType) {
            case LONGNVARCHAR /* -16 */:
            case NVARCHAR /* -9 */:
            case -1:
            case 12:
                return true;
            default:
                return false;
        }
    }

    public static boolean isVarbinaryType(int sqlType) {
        switch (sqlType) {
            case LONGVARBINARY /* -4 */:
            case -3:
                return true;
            default:
                return false;
        }
    }

    public static boolean isNumericOrDecimal(int typeCode) {
        return typeCode == 2 || typeCode == 3;
    }

    public static boolean isFloatOrRealOrDouble(int typeCode) {
        return typeCode == 6 || typeCode == 7 || typeCode == 8;
    }

    public static boolean isIntegral(int typeCode) {
        return typeCode == 4 || typeCode == -5 || typeCode == 5 || typeCode == -6;
    }

    public static boolean isTemporalType(int typeCode) {
        return typeCode == 91 || typeCode == 92 || typeCode == 93 || typeCode == 2014 || typeCode == 3003;
    }

    public static boolean hasDatePart(int typeCode) {
        return typeCode == 91 || typeCode == 93 || typeCode == 2014 || typeCode == 3003;
    }

    public static boolean hasTimePart(int typeCode) {
        return typeCode == 92 || typeCode == 93 || typeCode == 2014 || typeCode == 3003;
    }
}