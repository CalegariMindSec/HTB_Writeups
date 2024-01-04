package com.sun.xml.txw2.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PACKAGE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/txw2-4.0.1.jar:com/sun/xml/txw2/annotation/XmlNamespace.class */
public @interface XmlNamespace {
    String value();
}