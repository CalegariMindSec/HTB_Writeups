package io.micrometer.core.instrument.binder.cache;

import io.micrometer.common.lang.Nullable;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.ref.WeakReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/micrometer-core-1.10.3.jar:io/micrometer/core/instrument/binder/cache/HazelcastIMapAdapter.class */
public class HazelcastIMapAdapter {
    private static final Class<?> CLASS_I_MAP = resolveOneOf("com.hazelcast.map.IMap", "com.hazelcast.core.IMap");
    private static final Class<?> CLASS_LOCAL_MAP = resolveOneOf("com.hazelcast.map.LocalMapStats", "com.hazelcast.monitor.LocalMapStats");
    private static final Class<?> CLASS_NEAR_CACHE_STATS = resolveOneOf("com.hazelcast.nearcache.NearCacheStats", "com.hazelcast.monitor.NearCacheStats");
    private static final MethodHandle GET_NAME = resolveIMapMethod("getName", MethodType.methodType(String.class));
    private static final MethodHandle GET_LOCAL_MAP_STATS = resolveIMapMethod("getLocalMapStats", MethodType.methodType(CLASS_LOCAL_MAP));
    private final WeakReference<Object> cache;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HazelcastIMapAdapter(Object cache) {
        this.cache = new WeakReference<>(cache);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String nameOf(Object cache) {
        try {
            return (String) GET_NAME.invoke(cache);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public LocalMapStats getLocalMapStats() {
        Object result;
        Object ref = this.cache.get();
        if (ref == null || (result = invoke(GET_LOCAL_MAP_STATS, ref)) == null) {
            return null;
        }
        return new LocalMapStats(result);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/micrometer-core-1.10.3.jar:io/micrometer/core/instrument/binder/cache/HazelcastIMapAdapter$LocalMapStats.class */
    public static class LocalMapStats {
        private static final MethodHandle GET_NEAR_CACHE_STATS = resolveMethod("getNearCacheStats", MethodType.methodType(HazelcastIMapAdapter.CLASS_NEAR_CACHE_STATS));
        private static final MethodHandle GET_OWNED_ENTRY_COUNT = resolveMethod("getOwnedEntryCount", MethodType.methodType(Long.TYPE));
        private static final MethodHandle GET_HITS = resolveMethod("getHits", MethodType.methodType(Long.TYPE));
        private static final MethodHandle GET_PUT_OPERATION_COUNT = resolveMethod("getPutOperationCount", MethodType.methodType(Long.TYPE));
        private static final MethodHandle GET_BACKUP_ENTRY_COUNT = resolveMethod("getBackupEntryCount", MethodType.methodType(Long.TYPE));
        private static final MethodHandle GET_BACKUP_ENTRY_MEMORY_COST = resolveMethod("getBackupEntryMemoryCost", MethodType.methodType(Long.TYPE));
        private static final MethodHandle GET_OWNED_ENTRY_MEMORY_COST = resolveMethod("getOwnedEntryMemoryCost", MethodType.methodType(Long.TYPE));
        private static final MethodHandle GET_GET_OPERATION_COUNT = resolveMethod("getGetOperationCount", MethodType.methodType(Long.TYPE));
        private static final MethodHandle GET_TOTAL_GET_LATENCY = resolveMethod("getTotalGetLatency", MethodType.methodType(Long.TYPE));
        private static final MethodHandle GET_TOTAL_PUT_LATENCY = resolveMethod("getTotalPutLatency", MethodType.methodType(Long.TYPE));
        private static final MethodHandle GET_REMOVE_OPERATION_COUNT = resolveMethod("getRemoveOperationCount", MethodType.methodType(Long.TYPE));
        private static final MethodHandle GET_TOTAL_REMOVE_LATENCY = resolveMethod("getTotalRemoveLatency", MethodType.methodType(Long.TYPE));
        private final Object localMapStats;

        LocalMapStats(Object localMapStats) {
            this.localMapStats = localMapStats;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public long getOwnedEntryCount() {
            return ((Long) HazelcastIMapAdapter.invoke(GET_OWNED_ENTRY_COUNT, this.localMapStats)).longValue();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public long getHits() {
            return ((Long) HazelcastIMapAdapter.invoke(GET_HITS, this.localMapStats)).longValue();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public long getPutOperationCount() {
            return ((Long) HazelcastIMapAdapter.invoke(GET_PUT_OPERATION_COUNT, this.localMapStats)).longValue();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public double getBackupEntryCount() {
            return ((Long) HazelcastIMapAdapter.invoke(GET_BACKUP_ENTRY_COUNT, this.localMapStats)).longValue();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public long getBackupEntryMemoryCost() {
            return ((Long) HazelcastIMapAdapter.invoke(GET_BACKUP_ENTRY_MEMORY_COST, this.localMapStats)).longValue();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public long getOwnedEntryMemoryCost() {
            return ((Long) HazelcastIMapAdapter.invoke(GET_OWNED_ENTRY_MEMORY_COST, this.localMapStats)).longValue();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public long getGetOperationCount() {
            return ((Long) HazelcastIMapAdapter.invoke(GET_GET_OPERATION_COUNT, this.localMapStats)).longValue();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public NearCacheStats getNearCacheStats() {
            Object result = HazelcastIMapAdapter.invoke(GET_NEAR_CACHE_STATS, this.localMapStats);
            if (result == null) {
                return null;
            }
            return new NearCacheStats(result);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public long getTotalGetLatency() {
            return ((Long) HazelcastIMapAdapter.invoke(GET_TOTAL_GET_LATENCY, this.localMapStats)).longValue();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public long getTotalPutLatency() {
            return ((Long) HazelcastIMapAdapter.invoke(GET_TOTAL_PUT_LATENCY, this.localMapStats)).longValue();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public long getRemoveOperationCount() {
            return ((Long) HazelcastIMapAdapter.invoke(GET_REMOVE_OPERATION_COUNT, this.localMapStats)).longValue();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public long getTotalRemoveLatency() {
            return ((Long) HazelcastIMapAdapter.invoke(GET_TOTAL_REMOVE_LATENCY, this.localMapStats)).longValue();
        }

        private static MethodHandle resolveMethod(String name, MethodType mt) {
            try {
                return MethodHandles.publicLookup().findVirtual(HazelcastIMapAdapter.CLASS_LOCAL_MAP, name, mt);
            } catch (IllegalAccessException | NoSuchMethodException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/micrometer-core-1.10.3.jar:io/micrometer/core/instrument/binder/cache/HazelcastIMapAdapter$NearCacheStats.class */
    public static class NearCacheStats {
        private static final MethodHandle GET_HITS = resolveMethod("getHits", MethodType.methodType(Long.TYPE));
        private static final MethodHandle GET_MISSES = resolveMethod("getMisses", MethodType.methodType(Long.TYPE));
        private static final MethodHandle GET_EVICTIONS = resolveMethod("getEvictions", MethodType.methodType(Long.TYPE));
        private static final MethodHandle GET_PERSISTENCE_COUNT = resolveMethod("getPersistenceCount", MethodType.methodType(Long.TYPE));
        private Object nearCacheStats;

        NearCacheStats(Object nearCacheStats) {
            this.nearCacheStats = nearCacheStats;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public long getHits() {
            return ((Long) HazelcastIMapAdapter.invoke(GET_HITS, this.nearCacheStats)).longValue();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public long getMisses() {
            return ((Long) HazelcastIMapAdapter.invoke(GET_MISSES, this.nearCacheStats)).longValue();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public long getEvictions() {
            return ((Long) HazelcastIMapAdapter.invoke(GET_EVICTIONS, this.nearCacheStats)).longValue();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public long getPersistenceCount() {
            return ((Long) HazelcastIMapAdapter.invoke(GET_PERSISTENCE_COUNT, this.nearCacheStats)).longValue();
        }

        private static MethodHandle resolveMethod(String name, MethodType mt) {
            try {
                return MethodHandles.publicLookup().findVirtual(HazelcastIMapAdapter.CLASS_NEAR_CACHE_STATS, name, mt);
            } catch (IllegalAccessException | NoSuchMethodException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private static MethodHandle resolveIMapMethod(String name, MethodType mt) {
        try {
            return MethodHandles.publicLookup().findVirtual(CLASS_I_MAP, name, mt);
        } catch (IllegalAccessException | NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
    }

    private static Class<?> resolveOneOf(String class1, String class2) {
        try {
            return Class.forName(class1);
        } catch (ClassNotFoundException e) {
            try {
                return Class.forName(class2);
            } catch (ClassNotFoundException ex) {
                throw new IllegalStateException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object invoke(MethodHandle mh, Object object) {
        try {
            return (Object) mh.invoke(object);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
}