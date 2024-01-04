package com.sun.xml.txw2.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/txw2-4.0.1.jar:com/sun/xml/txw2/annotation/XmlElement.class */
public @interface XmlElement {
    String value() default "";

    String ns() default "##default";
}