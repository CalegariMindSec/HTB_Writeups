package com.sun.istack.localization;

import java.util.Locale;
import java.util.ResourceBundle;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/istack-commons-runtime-4.1.1.jar:com/sun/istack/localization/Localizable.class */
public interface Localizable {
    public static final String NOT_LOCALIZABLE = "��";

    String getKey();

    Object[] getArguments();

    String getResourceBundleName();

    ResourceBundle getResourceBundle(Locale locale);
}