package io.micrometer.core.instrument.binder.jvm;

import io.micrometer.common.lang.Nullable;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;
import java.util.function.ToLongFunction;
import java.util.stream.Stream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/micrometer-core-1.10.3.jar:io/micrometer/core/instrument/binder/jvm/JvmMemory.class */
public class JvmMemory {
    private JvmMemory() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Stream<MemoryPoolMXBean> getLongLivedHeapPools() {
        return ManagementFactory.getMemoryPoolMXBeans().stream().filter(JvmMemory::isHeap).filter(mem -> {
            return isLongLivedPool(mem.getName());
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isConcurrentPhase(String cause, String name) {
        return "No GC".equals(cause) || "Shenandoah Cycles".equals(name) || "ZGC Cycles".equals(name);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isAllocationPool(String name) {
        return name != null && (name.endsWith("Eden Space") || "Shenandoah".equals(name) || "ZHeap".equals(name) || name.endsWith("nursery-allocate") || name.endsWith("-eden") || "JavaHeap".equals(name));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isLongLivedPool(String name) {
        return name != null && (name.endsWith("Old Gen") || name.endsWith("Tenured Gen") || "Shenandoah".equals(name) || "ZHeap".equals(name) || name.endsWith("balanced-old") || name.contains("tenured") || "JavaHeap".equals(name));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isHeap(MemoryPoolMXBean memoryPoolBean) {
        return MemoryType.HEAP.equals(memoryPoolBean.getType());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double getUsageValue(MemoryPoolMXBean memoryPoolMXBean, ToLongFunction<MemoryUsage> getter) {
        MemoryUsage usage = getUsage(memoryPoolMXBean);
        if (usage == null) {
            return Double.NaN;
        }
        return getter.applyAsLong(usage);
    }

    @Nullable
    private static MemoryUsage getUsage(MemoryPoolMXBean memoryPoolMXBean) {
        try {
            return memoryPoolMXBean.getUsage();
        } catch (InternalError e) {
            return null;
        }
    }
}