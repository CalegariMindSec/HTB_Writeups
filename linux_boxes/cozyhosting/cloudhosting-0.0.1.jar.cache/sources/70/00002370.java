package org.aspectj.runtime.internal;

import org.glassfish.jaxb.runtime.v2.runtime.reflect.opt.Const;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/aspectjweaver-1.9.19.jar:org/aspectj/runtime/internal/Conversions.class */
public final class Conversions {
    private Conversions() {
    }

    public static Object intObject(int i) {
        return Integer.valueOf(i);
    }

    public static Object shortObject(short i) {
        return Short.valueOf(i);
    }

    public static Object byteObject(byte i) {
        return Byte.valueOf(i);
    }

    public static Object charObject(char i) {
        return Character.valueOf(i);
    }

    public static Object longObject(long i) {
        return Long.valueOf(i);
    }

    public static Object floatObject(float i) {
        return Float.valueOf(i);
    }

    public static Object doubleObject(double i) {
        return Double.valueOf(i);
    }

    public static Object booleanObject(boolean i) {
        return Boolean.valueOf(i);
    }

    public static Object voidObject() {
        return null;
    }

    public static int intValue(Object o) {
        if (o == null) {
            return 0;
        }
        if (o instanceof Number) {
            return ((Number) o).intValue();
        }
        throw new ClassCastException(o.getClass().getName() + " can not be converted to int");
    }

    public static long longValue(Object o) {
        if (o == null) {
            return 0L;
        }
        if (o instanceof Number) {
            return ((Number) o).longValue();
        }
        throw new ClassCastException(o.getClass().getName() + " can not be converted to long");
    }

    public static float floatValue(Object o) {
        if (o == null) {
            return Const.default_value_float;
        }
        if (o instanceof Number) {
            return ((Number) o).floatValue();
        }
        throw new ClassCastException(o.getClass().getName() + " can not be converted to float");
    }

    public static double doubleValue(Object o) {
        if (o == null) {
            return Const.default_value_double;
        }
        if (o instanceof Number) {
            return ((Number) o).doubleValue();
        }
        throw new ClassCastException(o.getClass().getName() + " can not be converted to double");
    }

    public static byte byteValue(Object o) {
        if (o == null) {
            return (byte) 0;
        }
        if (o instanceof Number) {
            return ((Number) o).byteValue();
        }
        throw new ClassCastException(o.getClass().getName() + " can not be converted to byte");
    }

    public static short shortValue(Object o) {
        if (o == null) {
            return (short) 0;
        }
        if (o instanceof Number) {
            return ((Number) o).shortValue();
        }
        throw new ClassCastException(o.getClass().getName() + " can not be converted to short");
    }

    public static char charValue(Object o) {
        if (o == null) {
            return (char) 0;
        }
        if (o instanceof Character) {
            return ((Character) o).charValue();
        }
        throw new ClassCastException(o.getClass().getName() + " can not be converted to char");
    }

    public static boolean booleanValue(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Boolean) {
            return ((Boolean) o).booleanValue();
        }
        throw new ClassCastException(o.getClass().getName() + " can not be converted to boolean");
    }

    public static Object voidValue(Object o) {
        if (o == null) {
            return o;
        }
        return o;
    }
}