package org.hibernate.engine.jdbc;

import java.io.Serializable;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/jdbc/Size.class */
public class Size implements Serializable {
    public static final long DEFAULT_LENGTH = 255;
    public static final long LONG_LENGTH = 32600;
    public static final long DEFAULT_LOB_LENGTH = 1048576;
    public static final int DEFAULT_PRECISION = 19;
    public static final int DEFAULT_SCALE = 2;
    private Integer precision;
    private Integer scale;
    private Long length;
    private LobMultiplier lobMultiplier;

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/hibernate-core-6.1.6.Final.jar:org/hibernate/engine/jdbc/Size$LobMultiplier.class */
    public enum LobMultiplier {
        NONE(1),
        K(NONE.factor * 1024),
        M(K.factor * 1024),
        G(M.factor * 1024);
        
        private final long factor;

        LobMultiplier(long factor) {
            this.factor = factor;
        }

        public long getFactor() {
            return this.factor;
        }
    }

    public Size() {
    }

    public Size(Integer precision, Integer scale, Long length, LobMultiplier lobMultiplier) {
        this.precision = precision;
        this.scale = scale;
        this.length = length;
        this.lobMultiplier = lobMultiplier;
    }

    public Size(Integer precision, Integer scale, Integer length, LobMultiplier lobMultiplier) {
        this.precision = precision;
        this.scale = scale;
        this.length = length == null ? null : Long.valueOf(length.longValue());
        this.lobMultiplier = lobMultiplier;
    }

    public static Size nil() {
        return new Size();
    }

    public static Size precision(int precision) {
        return new Size(Integer.valueOf(precision), (Integer) (-1), (Long) (-1L), (LobMultiplier) null);
    }

    public static Size precision(int precision, int scale) {
        return new Size(Integer.valueOf(precision), Integer.valueOf(scale), (Long) (-1L), (LobMultiplier) null);
    }

    public static Size length(long length) {
        return new Size((Integer) (-1), (Integer) (-1), Long.valueOf(length), (LobMultiplier) null);
    }

    public static Size length(long length, LobMultiplier lobMultiplier) {
        return new Size((Integer) (-1), (Integer) (-1), Long.valueOf(length), lobMultiplier);
    }

    public Integer getPrecision() {
        return this.precision;
    }

    public Integer getScale() {
        return this.scale;
    }

    public Long getLength() {
        return this.length;
    }

    public LobMultiplier getLobMultiplier() {
        return this.lobMultiplier;
    }

    public void initialize(Size size) {
        this.precision = size.precision;
        this.scale = size.scale;
        this.length = size.length;
    }

    public Size setPrecision(Integer precision) {
        this.precision = precision;
        return this;
    }

    public Size setScale(Integer scale) {
        this.scale = scale;
        return this;
    }

    public Size setLength(Long length) {
        this.length = length;
        return this;
    }

    public Size setLobMultiplier(LobMultiplier lobMultiplier) {
        this.lobMultiplier = lobMultiplier;
        return this;
    }
}