package lombok.patcher;

import java.util.Collection;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:lombok/patcher/TargetMatcher.SCL.lombok */
public interface TargetMatcher {
    Collection<String> getAffectedClasses();

    boolean matches(String str, String str2, String str3);

    String describe();
}