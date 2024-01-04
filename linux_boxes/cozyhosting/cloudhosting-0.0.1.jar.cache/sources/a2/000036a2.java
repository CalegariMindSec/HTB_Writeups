package org.hibernate.internal.util.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Function;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/collections/CollectionHelper.class */
public final class CollectionHelper {
    public static final int DEFAULT_LIST_CAPACITY = 10;
    public static final int MINIMUM_INITIAL_CAPACITY = 16;
    public static final float LOAD_FACTOR = 0.75f;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !CollectionHelper.class.desiredAssertionStatus();
    }

    private CollectionHelper() {
    }

    public static <K, V> HashMap<K, V> mapOfSize(int size) {
        return new HashMap<>(determineProperSizing(size), 0.75f);
    }

    public static <K, V> LinkedHashMap<K, V> linkedMapOfSize(int size) {
        return new LinkedHashMap<>(determineProperSizing(size), 0.75f);
    }

    public static <K, V> HashMap<K, V> map() {
        return new HashMap<>();
    }

    public static <K, V> LinkedHashMap<K, V> linkedMap() {
        return new LinkedHashMap<>();
    }

    public static <K> HashSet<K> setOfSize(int size) {
        return new HashSet<>(determineProperSizing(size), 0.75f);
    }

    public static <K> HashSet<K> set() {
        return new HashSet<>();
    }

    public static <K> LinkedHashSet<K> linkedSetOfSize(int size) {
        return new LinkedHashSet<>(determineProperSizing(size), 0.75f);
    }

    public static <K> LinkedHashSet<K> linkedSet() {
        return new LinkedHashSet<>();
    }

    public static int determineProperSizing(Map original) {
        return determineProperSizing(original.size());
    }

    public static <X, Y> Map<X, Y> makeCopy(Map<X, Y> map) {
        Map<X, Y> copy = mapOfSize(map.size() + 1);
        copy.putAll(map);
        return copy;
    }

    public static <K, V> HashMap<K, V> makeCopy(Map<K, V> original, Function<K, K> keyTransformer, Function<V, V> valueTransformer) {
        if (original == null) {
            return null;
        }
        HashMap<K, V> copy = new HashMap<>(determineProperSizing(original));
        original.forEach(key, value -> {
            copy.put(keyTransformer.apply(key), valueTransformer.apply(value));
        });
        return copy;
    }

    public static <K, V> Map<K, V> makeMap(Collection<V> collection, Function<V, K> keyProducer) {
        return makeMap(collection, keyProducer, v -> {
            return v;
        });
    }

    public static <K, V, E> Map<K, V> makeMap(Collection<E> collection, Function<E, K> keyProducer, Function<E, V> valueProducer) {
        if (isEmpty(collection)) {
            return Collections.emptyMap();
        }
        Map<K, V> map = new HashMap<>(determineProperSizing(collection.size()));
        for (E element : collection) {
            map.put(keyProducer.apply(element), valueProducer.apply(element));
        }
        return map;
    }

    public static int determineProperSizing(Set original) {
        return determineProperSizing(original.size());
    }

    public static int determineProperSizing(int numberOfElements) {
        int actual = ((int) (numberOfElements / 0.75f)) + 1;
        return Math.max(actual, 16);
    }

    public static <K, V> ConcurrentHashMap<K, V> concurrentMap(int expectedNumberOfElements) {
        return concurrentMap(expectedNumberOfElements, 0.75f);
    }

    public static <K, V> ConcurrentHashMap<K, V> concurrentMap(int expectedNumberOfElements, float loadFactor) {
        return new ConcurrentHashMap<>(expectedNumberOfElements, loadFactor);
    }

    public static <T> ArrayList<T> arrayList(int expectedNumberOfElements) {
        return new ArrayList<>(Math.max(expectedNumberOfElements + 1, 10));
    }

    public static <T> Set<T> makeCopy(Set<T> source) {
        if (source == null) {
            return null;
        }
        int size = source.size();
        Set<T> copy = setOfSize(size + 1);
        copy.addAll(source);
        return copy;
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    public static boolean isEmpty(Object[] objects) {
        return objects == null || objects.length == 0;
    }

    public static <T> List<T> listOf(T value1) {
        List<T> list = new ArrayList<>(1);
        list.add(value1);
        return list;
    }

    public static <T> List<T> listOf(T... values) {
        List<T> list = new ArrayList<>(values.length);
        Collections.addAll(list, values);
        return list;
    }

    public static <T> Set<T> setOf(T... values) {
        HashSet<T> set = new HashSet<>(determineProperSizing(values.length));
        Collections.addAll(set, values);
        return set;
    }

    public static Properties asProperties(Map<?, ?> map) {
        if (map instanceof Properties) {
            return (Properties) map;
        }
        Properties properties = new Properties();
        if (isNotEmpty(map)) {
            properties.putAll(map);
        }
        return properties;
    }

    public static <T> Set<T> toSmallSet(Set<T> set) {
        switch (set.size()) {
            case 0:
                return Collections.EMPTY_SET;
            case 1:
                return Collections.singleton(set.iterator().next());
            default:
                return set;
        }
    }

    public static <K, V> Map<K, V> toSmallMap(Map<K, V> map) {
        switch (map.size()) {
            case 0:
                return Collections.EMPTY_MAP;
            case 1:
                Map.Entry<K, V> entry = map.entrySet().iterator().next();
                return Collections.singletonMap(entry.getKey(), entry.getValue());
            default:
                return map;
        }
    }

    public static <V> List<V> toSmallList(ArrayList<V> arrayList) {
        switch (arrayList.size()) {
            case 0:
                return Collections.EMPTY_LIST;
            case 1:
                return Collections.singletonList(arrayList.get(0));
            default:
                arrayList.trimToSize();
                return arrayList;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <K, V> void collectMapEntries(BiConsumer<K, V> mapEntryConsumer, Object[] mappings) {
        if (!$assertionsDisabled && mappings.length % 2 != 0) {
            throw new AssertionError();
        }
        for (int i = 0; i < mappings.length; i += 2) {
            mapEntryConsumer.accept(mappings[i], mappings[i + 1]);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <K, S> Map<K, S> asMap(Object[] elements) {
        if ($assertionsDisabled || elements != null) {
            if ($assertionsDisabled || elements.length % 2 == 0) {
                HashMap hashMap = new HashMap();
                Objects.requireNonNull(hashMap);
                collectMapEntries(this::put, elements);
                for (int i = 0; i < elements.length; i += 2) {
                    hashMap.put(elements[i], elements[i + 1]);
                }
                return hashMap;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static Map<String, String> toMap(String... pairs) {
        if ($assertionsDisabled || pairs.length % 2 == 0) {
            if (pairs.length == 2) {
                return Collections.singletonMap(pairs[0], pairs[1]);
            }
            Map<String, String> result = new HashMap<>();
            applyToMap(result, pairs);
            return result;
        }
        throw new AssertionError();
    }

    private static void applyToMap(Map<String, String> map, String... pairs) {
        if (!$assertionsDisabled && pairs.length % 2 != 0) {
            throw new AssertionError();
        }
        for (int i = 0; i < pairs.length; i += 2) {
            map.put(pairs[i], pairs[i + 1]);
        }
    }

    public static Map<String, ?> toMap(Object... pairs) {
        if ($assertionsDisabled || pairs.length % 2 == 0) {
            if (pairs.length == 2) {
                return Collections.singletonMap((String) pairs[0], pairs[1]);
            }
            Map<String, String> result = new HashMap<>();
            applyToMap(result, pairs);
            return result;
        }
        throw new AssertionError();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void applyToMap(Map<String, ?> map, Object... pairs) {
        if (!$assertionsDisabled && pairs.length % 2 != 0) {
            throw new AssertionError();
        }
        for (int i = 0; i < pairs.length; i += 2) {
            map.put(pairs[i], pairs[i + 1]);
        }
    }

    public static Properties toProperties(Object... pairs) {
        Properties properties = new Properties();
        if (pairs.length > 0) {
            if (!$assertionsDisabled && pairs.length % 2 != 0) {
                throw new AssertionError();
            }
            for (int i = 0; i < pairs.length; i += 2) {
                properties.put(pairs[i], pairs[i + 1]);
            }
        }
        return properties;
    }

    public static void applyToProperties(Properties properties, Object... pairs) {
        if (!$assertionsDisabled && pairs.length % 2 != 0) {
            throw new AssertionError();
        }
        for (int i = 0; i < pairs.length; i += 2) {
            properties.put(pairs[i], pairs[i + 1]);
        }
    }
}