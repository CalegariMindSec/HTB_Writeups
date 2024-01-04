package lombok;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:lombok/EqualsAndHashCode.class */
public @interface EqualsAndHashCode {

    @Target({})
    @Retention(RetentionPolicy.SOURCE)
    @Deprecated
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:lombok/EqualsAndHashCode$AnyAnnotation.class */
    public @interface AnyAnnotation {
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:lombok/EqualsAndHashCode$CacheStrategy.class */
    public enum CacheStrategy {
        NEVER,
        LAZY;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static CacheStrategy[] valuesCustom() {
            CacheStrategy[] valuesCustom = values();
            int length = valuesCustom.length;
            CacheStrategy[] cacheStrategyArr = new CacheStrategy[length];
            System.arraycopy(valuesCustom, 0, cacheStrategyArr, 0, length);
            return cacheStrategyArr;
        }
    }

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:lombok/EqualsAndHashCode$Exclude.class */
    public @interface Exclude {
    }

    @Target({ElementType.FIELD, ElementType.METHOD})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:lombok/EqualsAndHashCode$Include.class */
    public @interface Include {
        String replaces() default "";

        int rank() default 0;
    }

    String[] exclude() default {};

    String[] of() default {};

    boolean callSuper() default false;

    boolean doNotUseGetters() default false;

    CacheStrategy cacheStrategy() default CacheStrategy.NEVER;

    AnyAnnotation[] onParam() default {};

    boolean onlyExplicitlyIncluded() default false;
}