package lombok.extern.jbosslog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:lombok/extern/jbosslog/JBossLog.class */
public @interface JBossLog {
    String topic() default "";
}