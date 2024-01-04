package org.hibernate.boot.model.source.spi;

import java.util.Set;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/model/source/spi/MetadataSourceProcessor.class */
public interface MetadataSourceProcessor {
    void prepare();

    void processTypeDefinitions();

    void processQueryRenames();

    void processNamedQueries();

    void processAuxiliaryDatabaseObjectDefinitions();

    void processIdentifierGenerators();

    void processFilterDefinitions();

    void processFetchProfiles();

    void prepareForEntityHierarchyProcessing();

    void processEntityHierarchies(Set<String> set);

    void postProcessEntityHierarchies();

    void processResultSetMappings();

    void finishUp();
}