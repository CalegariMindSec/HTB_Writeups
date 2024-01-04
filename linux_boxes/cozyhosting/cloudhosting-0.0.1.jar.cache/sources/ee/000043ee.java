package org.postgresql.shaded.com.ongres.scram.common.stringprep;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/postgresql-42.5.1.jar:org/postgresql/shaded/com/ongres/scram/common/stringprep/StringPreparation.class */
public interface StringPreparation {
    String normalize(String str) throws IllegalArgumentException;
}