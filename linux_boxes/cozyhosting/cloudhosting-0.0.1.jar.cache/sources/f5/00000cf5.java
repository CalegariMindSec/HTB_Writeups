package lombok.core.configuration;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/lombok-1.18.26.jar:lombok/core/configuration/CapitalizationStrategy.SCL.lombok */
public enum CapitalizationStrategy {
    BASIC { // from class: lombok.core.configuration.CapitalizationStrategy.1
        @Override // lombok.core.configuration.CapitalizationStrategy
        public String capitalize(String in) {
            if (in.length() == 0) {
                return in;
            }
            char first = in.charAt(0);
            if (Character.isLowerCase(first)) {
                boolean useUpperCase = in.length() > 2 && (Character.isTitleCase(in.charAt(1)) || Character.isUpperCase(in.charAt(1)));
                return String.valueOf(useUpperCase ? Character.toUpperCase(first) : Character.toTitleCase(first)) + in.substring(1);
            }
            return in;
        }
    },
    BEANSPEC { // from class: lombok.core.configuration.CapitalizationStrategy.2
        @Override // lombok.core.configuration.CapitalizationStrategy
        public String capitalize(String in) {
            if (in.length() == 0) {
                return in;
            }
            char first = in.charAt(0);
            if (!Character.isLowerCase(first) || (in.length() > 1 && Character.isUpperCase(in.charAt(1)))) {
                return in;
            }
            boolean useUpperCase = in.length() > 2 && Character.isTitleCase(in.charAt(1));
            return String.valueOf(useUpperCase ? Character.toUpperCase(first) : Character.toTitleCase(first)) + in.substring(1);
        }
    };

    public abstract String capitalize(String str);

    /* renamed from: values  reason: to resolve conflict with enum method */
    public static CapitalizationStrategy[] valuesCustom() {
        CapitalizationStrategy[] valuesCustom = values();
        int length = valuesCustom.length;
        CapitalizationStrategy[] capitalizationStrategyArr = new CapitalizationStrategy[length];
        System.arraycopy(valuesCustom, 0, capitalizationStrategyArr, 0, length);
        return capitalizationStrategyArr;
    }

    /* synthetic */ CapitalizationStrategy(CapitalizationStrategy capitalizationStrategy) {
        this();
    }

    public static CapitalizationStrategy defaultValue() {
        return BASIC;
    }
}