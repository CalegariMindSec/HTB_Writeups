package org.checkerframework.checker.nullness;

import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/checker-qual-3.5.0.jar:org/checkerframework/checker/nullness/NullnessUtil.class */
public final class NullnessUtil {
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !NullnessUtil.class.desiredAssertionStatus();
    }

    private NullnessUtil() {
        throw new AssertionError("shouldn't be instantiated");
    }

    @EnsuresNonNull({"#1"})
    public static <T> T castNonNull(T ref) {
        if ($assertionsDisabled || ref != null) {
            return ref;
        }
        throw new AssertionError("Misuse of castNonNull: called with a null argument");
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[] castNonNullDeep(T[] arr) {
        return (T[]) castNonNullArray(arr);
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[][] castNonNullDeep(T[][] arr) {
        return (T[][]) ((Object[][]) castNonNullArray(arr));
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[][][] castNonNullDeep(T[][][] arr) {
        return (T[][][]) ((Object[][][]) castNonNullArray(arr));
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[][][][] castNonNullDeep(T[][][][] arr) {
        return (T[][][][]) ((Object[][][][]) castNonNullArray(arr));
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[][][][][] castNonNullDeep(T[][][][][] arr) {
        return (T[][][][][]) ((Object[][][][][]) castNonNullArray(arr));
    }

    private static <T> T[] castNonNullArray(T[] arr) {
        if ($assertionsDisabled || arr != null) {
            for (int i = 0; i < arr.length; i++) {
                if (!$assertionsDisabled && arr[i] == null) {
                    throw new AssertionError("Misuse of castNonNull: called with a null array element");
                }
                checkIfArray(arr[i]);
            }
            return arr;
        }
        throw new AssertionError("Misuse of castNonNullArray: called with a null array argument");
    }

    private static void checkIfArray(Object ref) {
        if (!$assertionsDisabled && ref == null) {
            throw new AssertionError("Misuse of checkIfArray: called with a null argument");
        }
        Class<?> comp = ref.getClass().getComponentType();
        if (comp != null && !comp.isPrimitive()) {
            castNonNullArray((Object[]) ref);
        }
    }
}