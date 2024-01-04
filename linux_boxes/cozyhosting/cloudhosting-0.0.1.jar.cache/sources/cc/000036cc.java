package org.hibernate.internal.util.type;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/type/PrimitiveWrapperHelper.class */
public final class PrimitiveWrapperHelper {

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/type/PrimitiveWrapperHelper$PrimitiveWrapperDescriptor.class */
    public interface PrimitiveWrapperDescriptor<T> {
        Class<T> getPrimitiveClass();

        Class<T> getWrapperClass();
    }

    private PrimitiveWrapperHelper() {
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/type/PrimitiveWrapperHelper$BooleanDescriptor.class */
    public static class BooleanDescriptor implements PrimitiveWrapperDescriptor<Boolean> {
        public static final BooleanDescriptor INSTANCE = new BooleanDescriptor();

        private BooleanDescriptor() {
        }

        @Override // org.hibernate.internal.util.type.PrimitiveWrapperHelper.PrimitiveWrapperDescriptor
        public Class<Boolean> getPrimitiveClass() {
            return Boolean.TYPE;
        }

        @Override // org.hibernate.internal.util.type.PrimitiveWrapperHelper.PrimitiveWrapperDescriptor
        public Class<Boolean> getWrapperClass() {
            return Boolean.class;
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/type/PrimitiveWrapperHelper$CharacterDescriptor.class */
    public static class CharacterDescriptor implements PrimitiveWrapperDescriptor<Character> {
        public static final CharacterDescriptor INSTANCE = new CharacterDescriptor();

        private CharacterDescriptor() {
        }

        @Override // org.hibernate.internal.util.type.PrimitiveWrapperHelper.PrimitiveWrapperDescriptor
        public Class<Character> getPrimitiveClass() {
            return Character.TYPE;
        }

        @Override // org.hibernate.internal.util.type.PrimitiveWrapperHelper.PrimitiveWrapperDescriptor
        public Class<Character> getWrapperClass() {
            return Character.class;
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/type/PrimitiveWrapperHelper$ByteDescriptor.class */
    public static class ByteDescriptor implements PrimitiveWrapperDescriptor<Byte> {
        public static final ByteDescriptor INSTANCE = new ByteDescriptor();

        private ByteDescriptor() {
        }

        @Override // org.hibernate.internal.util.type.PrimitiveWrapperHelper.PrimitiveWrapperDescriptor
        public Class<Byte> getPrimitiveClass() {
            return Byte.TYPE;
        }

        @Override // org.hibernate.internal.util.type.PrimitiveWrapperHelper.PrimitiveWrapperDescriptor
        public Class<Byte> getWrapperClass() {
            return Byte.class;
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/type/PrimitiveWrapperHelper$ShortDescriptor.class */
    public static class ShortDescriptor implements PrimitiveWrapperDescriptor<Short> {
        public static final ShortDescriptor INSTANCE = new ShortDescriptor();

        private ShortDescriptor() {
        }

        @Override // org.hibernate.internal.util.type.PrimitiveWrapperHelper.PrimitiveWrapperDescriptor
        public Class<Short> getPrimitiveClass() {
            return Short.TYPE;
        }

        @Override // org.hibernate.internal.util.type.PrimitiveWrapperHelper.PrimitiveWrapperDescriptor
        public Class<Short> getWrapperClass() {
            return Short.class;
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/type/PrimitiveWrapperHelper$IntegerDescriptor.class */
    public static class IntegerDescriptor implements PrimitiveWrapperDescriptor<Integer> {
        public static final IntegerDescriptor INSTANCE = new IntegerDescriptor();

        private IntegerDescriptor() {
        }

        @Override // org.hibernate.internal.util.type.PrimitiveWrapperHelper.PrimitiveWrapperDescriptor
        public Class<Integer> getPrimitiveClass() {
            return Integer.TYPE;
        }

        @Override // org.hibernate.internal.util.type.PrimitiveWrapperHelper.PrimitiveWrapperDescriptor
        public Class<Integer> getWrapperClass() {
            return Integer.class;
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/type/PrimitiveWrapperHelper$LongDescriptor.class */
    public static class LongDescriptor implements PrimitiveWrapperDescriptor<Long> {
        public static final LongDescriptor INSTANCE = new LongDescriptor();

        private LongDescriptor() {
        }

        @Override // org.hibernate.internal.util.type.PrimitiveWrapperHelper.PrimitiveWrapperDescriptor
        public Class<Long> getPrimitiveClass() {
            return Long.TYPE;
        }

        @Override // org.hibernate.internal.util.type.PrimitiveWrapperHelper.PrimitiveWrapperDescriptor
        public Class<Long> getWrapperClass() {
            return Long.class;
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/type/PrimitiveWrapperHelper$FloatDescriptor.class */
    public static class FloatDescriptor implements PrimitiveWrapperDescriptor<Float> {
        public static final FloatDescriptor INSTANCE = new FloatDescriptor();

        private FloatDescriptor() {
        }

        @Override // org.hibernate.internal.util.type.PrimitiveWrapperHelper.PrimitiveWrapperDescriptor
        public Class<Float> getPrimitiveClass() {
            return Float.TYPE;
        }

        @Override // org.hibernate.internal.util.type.PrimitiveWrapperHelper.PrimitiveWrapperDescriptor
        public Class<Float> getWrapperClass() {
            return Float.class;
        }
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/internal/util/type/PrimitiveWrapperHelper$DoubleDescriptor.class */
    public static class DoubleDescriptor implements PrimitiveWrapperDescriptor<Double> {
        public static final DoubleDescriptor INSTANCE = new DoubleDescriptor();

        private DoubleDescriptor() {
        }

        @Override // org.hibernate.internal.util.type.PrimitiveWrapperHelper.PrimitiveWrapperDescriptor
        public Class<Double> getPrimitiveClass() {
            return Double.TYPE;
        }

        @Override // org.hibernate.internal.util.type.PrimitiveWrapperHelper.PrimitiveWrapperDescriptor
        public Class<Double> getWrapperClass() {
            return Double.class;
        }
    }

    public static <X> PrimitiveWrapperDescriptor<X> getDescriptorByPrimitiveType(Class<X> primitiveClazz) {
        if (!primitiveClazz.isPrimitive()) {
            throw new IllegalArgumentException("Given class is not a primitive type : " + primitiveClazz.getName());
        }
        if (Boolean.TYPE == primitiveClazz) {
            return BooleanDescriptor.INSTANCE;
        }
        if (Character.TYPE == primitiveClazz) {
            return CharacterDescriptor.INSTANCE;
        }
        if (Byte.TYPE == primitiveClazz) {
            return ByteDescriptor.INSTANCE;
        }
        if (Short.TYPE == primitiveClazz) {
            return ShortDescriptor.INSTANCE;
        }
        if (Integer.TYPE == primitiveClazz) {
            return IntegerDescriptor.INSTANCE;
        }
        if (Long.TYPE == primitiveClazz) {
            return LongDescriptor.INSTANCE;
        }
        if (Float.TYPE == primitiveClazz) {
            return FloatDescriptor.INSTANCE;
        }
        if (Double.TYPE == primitiveClazz) {
            return DoubleDescriptor.INSTANCE;
        }
        if (Void.TYPE == primitiveClazz) {
            throw new IllegalArgumentException("void, as primitive type, has no wrapper equivalent");
        }
        throw new IllegalArgumentException("Unrecognized primitive type class : " + primitiveClazz.getName());
    }

    public static <X> PrimitiveWrapperDescriptor<X> getDescriptorByWrapperType(Class<X> wrapperClass) {
        if (wrapperClass.isPrimitive()) {
            throw new IllegalArgumentException("Given class is a primitive type : " + wrapperClass.getName());
        }
        if (Boolean.class.equals(wrapperClass)) {
            return BooleanDescriptor.INSTANCE;
        }
        if (Character.class == wrapperClass) {
            return CharacterDescriptor.INSTANCE;
        }
        if (Byte.class == wrapperClass) {
            return ByteDescriptor.INSTANCE;
        }
        if (Short.class == wrapperClass) {
            return ShortDescriptor.INSTANCE;
        }
        if (Integer.class == wrapperClass) {
            return IntegerDescriptor.INSTANCE;
        }
        if (Long.class == wrapperClass) {
            return LongDescriptor.INSTANCE;
        }
        if (Float.class == wrapperClass) {
            return FloatDescriptor.INSTANCE;
        }
        if (Double.class == wrapperClass) {
            return DoubleDescriptor.INSTANCE;
        }
        throw new IllegalArgumentException("Unrecognized wrapper type class : " + wrapperClass.getName());
    }

    public static boolean isWrapper(Class<?> clazz) {
        try {
            getDescriptorByWrapperType(clazz);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean arePrimitiveWrapperEquivalents(Class converterDefinedType, Class propertyType) {
        if (converterDefinedType.isPrimitive()) {
            return getDescriptorByPrimitiveType(converterDefinedType).getWrapperClass().equals(propertyType);
        }
        if (propertyType.isPrimitive()) {
            return getDescriptorByPrimitiveType(propertyType).getWrapperClass().equals(converterDefinedType);
        }
        return false;
    }
}