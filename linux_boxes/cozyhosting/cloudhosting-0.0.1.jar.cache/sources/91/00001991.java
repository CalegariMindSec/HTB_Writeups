package net.bytebuddy.utility;

import java.lang.reflect.Constructor;
import java.util.Comparator;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/utility/ConstructorComparator.class */
public enum ConstructorComparator implements Comparator<Constructor<?>> {
    INSTANCE;

    @Override // java.util.Comparator
    public int compare(Constructor<?> left, Constructor<?> right) {
        if (left == right) {
            return 0;
        }
        int comparison = left.getName().compareTo(right.getName());
        if (comparison == 0) {
            Class<?>[] leftParameterType = left.getParameterTypes();
            Class<?>[] rightParameterType = right.getParameterTypes();
            if (leftParameterType.length < rightParameterType.length) {
                return -1;
            }
            if (leftParameterType.length > rightParameterType.length) {
                return 1;
            }
            for (int index = 0; index < leftParameterType.length; index++) {
                int comparisonParameterType = leftParameterType[index].getName().compareTo(rightParameterType[index].getName());
                if (comparisonParameterType != 0) {
                    return comparisonParameterType;
                }
            }
            return 0;
        }
        return comparison;
    }
}