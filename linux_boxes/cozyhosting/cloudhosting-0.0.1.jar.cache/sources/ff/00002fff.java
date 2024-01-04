package org.hibernate.cache.spi;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/cache/spi/QuerySpacesHelper.class */
public class QuerySpacesHelper {
    public static final QuerySpacesHelper INSTANCE = new QuerySpacesHelper();

    private QuerySpacesHelper() {
    }

    public String[] toStringArray(Set spacesSet) {
        return (String[]) spacesSet.toArray(new String[0]);
    }

    public Set<String> toStringSet(String[] spacesArray) {
        HashSet<String> set = new HashSet<>();
        Collections.addAll(set, spacesArray);
        return set;
    }

    public Set<Serializable> toSerializableSet(String[] spacesArray) {
        HashSet<Serializable> set = new HashSet<>();
        Collections.addAll(set, spacesArray);
        return set;
    }
}