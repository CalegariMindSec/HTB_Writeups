package lombok.core.runtimeDependencies;

import java.util.List;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:lombok/core/runtimeDependencies/RuntimeDependencyInfo.SCL.lombok */
public interface RuntimeDependencyInfo {
    List<String> getRuntimeDependentsDescriptions();

    List<String> getRuntimeDependencies();
}