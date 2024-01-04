package ch.qos.logback.core.testUtil;

import java.util.HashMap;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/logback-core-1.4.5.jar:ch/qos/logback/core/testUtil/MockInitialContext.class */
public class MockInitialContext extends InitialContext {
    public Map<String, Object> map = new HashMap();

    public Object lookup(String name) throws NamingException {
        if (name == null) {
            return null;
        }
        return this.map.get(name);
    }
}