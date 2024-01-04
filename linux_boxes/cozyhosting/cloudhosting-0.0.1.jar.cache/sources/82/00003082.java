package org.hibernate.cfg;

import java.io.Serializable;
import java.util.Map;
import org.hibernate.MappingException;
import org.hibernate.mapping.PersistentClass;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/cfg/SecondPass.class */
public interface SecondPass extends Serializable {
    void doSecondPass(Map<String, PersistentClass> map) throws MappingException;
}