package org.HdrHistogram;

import org.glassfish.jaxb.runtime.v2.runtime.reflect.opt.Const;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/HdrHistogram-2.1.12.jar:org/HdrHistogram/HistogramIterationValue.class */
public class HistogramIterationValue {
    private long valueIteratedTo;
    private long valueIteratedFrom;
    private long countAtValueIteratedTo;
    private long countAddedInThisIterationStep;
    private long totalCountToThisValue;
    private long totalValueToThisValue;
    private double percentile;
    private double percentileLevelIteratedTo;
    private double integerToDoubleValueConversionRatio;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void set(long valueIteratedTo, long valueIteratedFrom, long countAtValueIteratedTo, long countInThisIterationStep, long totalCountToThisValue, long totalValueToThisValue, double percentile, double percentileLevelIteratedTo, double integerToDoubleValueConversionRatio) {
        this.valueIteratedTo = valueIteratedTo;
        this.valueIteratedFrom = valueIteratedFrom;
        this.countAtValueIteratedTo = countAtValueIteratedTo;
        this.countAddedInThisIterationStep = countInThisIterationStep;
        this.totalCountToThisValue = totalCountToThisValue;
        this.totalValueToThisValue = totalValueToThisValue;
        this.percentile = percentile;
        this.percentileLevelIteratedTo = percentileLevelIteratedTo;
        this.integerToDoubleValueConversionRatio = integerToDoubleValueConversionRatio;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reset() {
        this.valueIteratedTo = 0L;
        this.valueIteratedFrom = 0L;
        this.countAtValueIteratedTo = 0L;
        this.countAddedInThisIterationStep = 0L;
        this.totalCountToThisValue = 0L;
        this.totalValueToThisValue = 0L;
        this.percentile = Const.default_value_double;
        this.percentileLevelIteratedTo = Const.default_value_double;
    }

    public String toString() {
        return "valueIteratedTo:" + this.valueIteratedTo + ", prevValueIteratedTo:" + this.valueIteratedFrom + ", countAtValueIteratedTo:" + this.countAtValueIteratedTo + ", countAddedInThisIterationStep:" + this.countAddedInThisIterationStep + ", totalCountToThisValue:" + this.totalCountToThisValue + ", totalValueToThisValue:" + this.totalValueToThisValue + ", percentile:" + this.percentile + ", percentileLevelIteratedTo:" + this.percentileLevelIteratedTo;
    }

    public long getValueIteratedTo() {
        return this.valueIteratedTo;
    }

    public double getDoubleValueIteratedTo() {
        return this.valueIteratedTo * this.integerToDoubleValueConversionRatio;
    }

    public long getValueIteratedFrom() {
        return this.valueIteratedFrom;
    }

    public double getDoubleValueIteratedFrom() {
        return this.valueIteratedFrom * this.integerToDoubleValueConversionRatio;
    }

    public long getCountAtValueIteratedTo() {
        return this.countAtValueIteratedTo;
    }

    public long getCountAddedInThisIterationStep() {
        return this.countAddedInThisIterationStep;
    }

    public long getTotalCountToThisValue() {
        return this.totalCountToThisValue;
    }

    public long getTotalValueToThisValue() {
        return this.totalValueToThisValue;
    }

    public double getPercentile() {
        return this.percentile;
    }

    public double getPercentileLevelIteratedTo() {
        return this.percentileLevelIteratedTo;
    }

    public double getIntegerToDoubleValueConversionRatio() {
        return this.integerToDoubleValueConversionRatio;
    }
}