package org.checkerframework.framework.qual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/checker-qual-3.5.0.jar:org/checkerframework/framework/qual/LiteralKind.class */
public enum LiteralKind {
    NULL,
    INT,
    LONG,
    FLOAT,
    DOUBLE,
    BOOLEAN,
    CHAR,
    STRING,
    ALL,
    PRIMITIVE;

    public static List<LiteralKind> allLiteralKinds() {
        List<LiteralKind> list = new ArrayList<>(Arrays.asList(values()));
        list.remove(ALL);
        list.remove(PRIMITIVE);
        return list;
    }

    public static List<LiteralKind> primitiveLiteralKinds() {
        return new ArrayList(Arrays.asList(INT, LONG, FLOAT, DOUBLE, BOOLEAN, CHAR));
    }
}