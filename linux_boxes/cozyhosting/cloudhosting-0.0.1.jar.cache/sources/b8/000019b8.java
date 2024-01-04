package net.bytebuddy.utility;

import java.lang.reflect.Method;
import java.util.Comparator;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/utility/MethodComparator.class */
public enum MethodComparator implements Comparator<Method> {
    INSTANCE;

    @Override // java.util.Comparator
    public int compare(Method left, Method right) {
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
            return left.getReturnType().getName().compareTo(right.getReturnType().getName());
        }
        return comparison;
    }
}