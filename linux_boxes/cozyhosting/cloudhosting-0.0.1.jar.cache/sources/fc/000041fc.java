package org.jboss.jandex;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.unbescape.uri.UriEscape;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jandex-2.4.2.Final.jar:org/jboss/jandex/Utils.class */
class Utils {
    private static Charset UTF8 = Charset.forName(UriEscape.DEFAULT_ENCODING);

    Utils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] toUTF8(String string) {
        return string.getBytes(UTF8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String fromUTF8(byte[] bytes) {
        return new String(bytes, UTF8);
    }

    static <T> List<T> emptyOrWrap(List<T> list) {
        return list.size() == 0 ? Collections.emptyList() : Collections.unmodifiableList(list);
    }

    static <T> Collection<T> emptyOrWrap(Collection<T> list) {
        return list.size() == 0 ? Collections.emptyList() : Collections.unmodifiableCollection(list);
    }

    static <K, V> Map<K, V> emptyOrWrap(Map<K, V> map) {
        return map.size() == 0 ? Collections.emptyMap() : Collections.unmodifiableMap(map);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> List<T> listOfCapacity(int capacity) {
        return capacity > 0 ? new ArrayList(capacity) : Collections.emptyList();
    }
}