package org.antlr.v4.runtime.misc;

import org.postgresql.core.Oid;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/antlr4-runtime-4.10.1.jar:org/antlr/v4/runtime/misc/Interval.class */
public class Interval {
    public static final int INTERVAL_POOL_MAX_VALUE = 1000;
    public int a;
    public int b;
    public static final Interval INVALID = new Interval(-1, -2);
    static Interval[] cache = new Interval[Oid.BYTEA_ARRAY];
    public static int creates = 0;
    public static int misses = 0;
    public static int hits = 0;
    public static int outOfRange = 0;

    public Interval(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public static Interval of(int a, int b) {
        if (a != b || a < 0 || a > 1000) {
            return new Interval(a, b);
        }
        if (cache[a] == null) {
            cache[a] = new Interval(a, a);
        }
        return cache[a];
    }

    public int length() {
        if (this.b < this.a) {
            return 0;
        }
        return (this.b - this.a) + 1;
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof Interval)) {
            return false;
        }
        Interval other = (Interval) o;
        return this.a == other.a && this.b == other.b;
    }

    public int hashCode() {
        int hash = (23 * 31) + this.a;
        return (hash * 31) + this.b;
    }

    public boolean startsBeforeDisjoint(Interval other) {
        return this.a < other.a && this.b < other.a;
    }

    public boolean startsBeforeNonDisjoint(Interval other) {
        return this.a <= other.a && this.b >= other.a;
    }

    public boolean startsAfter(Interval other) {
        return this.a > other.a;
    }

    public boolean startsAfterDisjoint(Interval other) {
        return this.a > other.b;
    }

    public boolean startsAfterNonDisjoint(Interval other) {
        return this.a > other.a && this.a <= other.b;
    }

    public boolean disjoint(Interval other) {
        return startsBeforeDisjoint(other) || startsAfterDisjoint(other);
    }

    public boolean adjacent(Interval other) {
        return this.a == other.b + 1 || this.b == other.a - 1;
    }

    public boolean properlyContains(Interval other) {
        return other.a >= this.a && other.b <= this.b;
    }

    public Interval union(Interval other) {
        return of(Math.min(this.a, other.a), Math.max(this.b, other.b));
    }

    public Interval intersection(Interval other) {
        return of(Math.max(this.a, other.a), Math.min(this.b, other.b));
    }

    public Interval differenceNotProperlyContained(Interval other) {
        Interval diff = null;
        if (other.startsBeforeNonDisjoint(this)) {
            diff = of(Math.max(this.a, other.b + 1), this.b);
        } else if (other.startsAfterNonDisjoint(this)) {
            diff = of(this.a, other.a - 1);
        }
        return diff;
    }

    public String toString() {
        return this.a + ".." + this.b;
    }
}