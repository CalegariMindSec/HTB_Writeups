package org.hibernate.jpa.event.spi;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import java.lang.annotation.Annotation;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/jpa/event/spi/CallbackType.class */
public enum CallbackType {
    PRE_UPDATE(PreUpdate.class),
    POST_UPDATE(PostUpdate.class),
    PRE_PERSIST(PrePersist.class),
    POST_PERSIST(PostPersist.class),
    PRE_REMOVE(PreRemove.class),
    POST_REMOVE(PostRemove.class),
    POST_LOAD(PostLoad.class);
    
    private final Class<? extends Annotation> callbackAnnotation;

    CallbackType(Class cls) {
        this.callbackAnnotation = cls;
    }

    public Class<? extends Annotation> getCallbackAnnotation() {
        return this.callbackAnnotation;
    }
}