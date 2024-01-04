package org.hibernate.tuple;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/tuple/GenerationTiming.class */
public enum GenerationTiming {
    NEVER { // from class: org.hibernate.tuple.GenerationTiming.1
        @Override // org.hibernate.tuple.GenerationTiming
        public boolean includesInsert() {
            return false;
        }

        @Override // org.hibernate.tuple.GenerationTiming
        public boolean includesUpdate() {
            return false;
        }

        @Override // org.hibernate.tuple.GenerationTiming
        public boolean includes(GenerationTiming timing) {
            return false;
        }
    },
    INSERT { // from class: org.hibernate.tuple.GenerationTiming.2
        @Override // org.hibernate.tuple.GenerationTiming
        public boolean includesInsert() {
            return true;
        }

        @Override // org.hibernate.tuple.GenerationTiming
        public boolean includesUpdate() {
            return false;
        }

        @Override // org.hibernate.tuple.GenerationTiming
        public boolean includes(GenerationTiming timing) {
            return timing.includesInsert();
        }
    },
    ALWAYS { // from class: org.hibernate.tuple.GenerationTiming.3
        @Override // org.hibernate.tuple.GenerationTiming
        public boolean includesInsert() {
            return true;
        }

        @Override // org.hibernate.tuple.GenerationTiming
        public boolean includesUpdate() {
            return true;
        }

        @Override // org.hibernate.tuple.GenerationTiming
        public boolean includes(GenerationTiming timing) {
            return timing != NEVER;
        }
    };

    public abstract boolean includesInsert();

    public abstract boolean includesUpdate();

    public abstract boolean includes(GenerationTiming generationTiming);

    public static GenerationTiming parseFromName(String name) {
        if ("insert".equalsIgnoreCase(name)) {
            return INSERT;
        }
        if ("always".equalsIgnoreCase(name)) {
            return ALWAYS;
        }
        return NEVER;
    }
}