package org.jboss.logging;

import java.util.Locale;

@Deprecated
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jboss-logging-3.5.0.Final.jar:org/jboss/logging/ParameterConverter.class */
public interface ParameterConverter<I> {
    Object convert(Locale locale, I i);
}