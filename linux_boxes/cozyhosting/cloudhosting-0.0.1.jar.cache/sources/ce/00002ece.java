package org.hibernate.boot.model.source.spi;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/model/source/spi/RelationalValueSource.class */
public interface RelationalValueSource {
    String getContainingTableName();

    Nature getNature();

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/boot/model/source/spi/RelationalValueSource$Nature.class */
    public enum Nature {
        COLUMN(ColumnSource.class),
        DERIVED(DerivedValueSource.class);
        
        private final Class<? extends RelationalValueSource> specificContractClass;

        Nature(Class cls) {
            this.specificContractClass = cls;
        }

        public Class<? extends RelationalValueSource> getSpecificContractClass() {
            return this.specificContractClass;
        }
    }
}