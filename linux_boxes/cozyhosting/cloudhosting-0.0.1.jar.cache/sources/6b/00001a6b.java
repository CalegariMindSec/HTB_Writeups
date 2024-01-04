package org.antlr.v4.runtime;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/antlr4-runtime-4.10.1.jar:org/antlr/v4/runtime/CodePointBuffer.class */
public class CodePointBuffer {
    private final Type type;
    private final ByteBuffer byteBuffer;
    private final CharBuffer charBuffer;
    private final IntBuffer intBuffer;
    static final /* synthetic */ boolean $assertionsDisabled;

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/antlr4-runtime-4.10.1.jar:org/antlr/v4/runtime/CodePointBuffer$Type.class */
    public enum Type {
        BYTE,
        CHAR,
        INT
    }

    static {
        $assertionsDisabled = !CodePointBuffer.class.desiredAssertionStatus();
    }

    private CodePointBuffer(Type type, ByteBuffer byteBuffer, CharBuffer charBuffer, IntBuffer intBuffer) {
        this.type = type;
        this.byteBuffer = byteBuffer;
        this.charBuffer = charBuffer;
        this.intBuffer = intBuffer;
    }

    public static CodePointBuffer withBytes(ByteBuffer byteBuffer) {
        return new CodePointBuffer(Type.BYTE, byteBuffer, null, null);
    }

    public static CodePointBuffer withChars(CharBuffer charBuffer) {
        return new CodePointBuffer(Type.CHAR, null, charBuffer, null);
    }

    public static CodePointBuffer withInts(IntBuffer intBuffer) {
        return new CodePointBuffer(Type.INT, null, null, intBuffer);
    }

    public int position() {
        switch (this.type) {
            case BYTE:
                return this.byteBuffer.position();
            case CHAR:
                return this.charBuffer.position();
            case INT:
                return this.intBuffer.position();
            default:
                throw new UnsupportedOperationException("Not reached");
        }
    }

    public void position(int newPosition) {
        switch (this.type) {
            case BYTE:
                this.byteBuffer.position(newPosition);
                return;
            case CHAR:
                this.charBuffer.position(newPosition);
                return;
            case INT:
                this.intBuffer.position(newPosition);
                return;
            default:
                return;
        }
    }

    public int remaining() {
        switch (this.type) {
            case BYTE:
                return this.byteBuffer.remaining();
            case CHAR:
                return this.charBuffer.remaining();
            case INT:
                return this.intBuffer.remaining();
            default:
                throw new UnsupportedOperationException("Not reached");
        }
    }

    public int get(int offset) {
        switch (this.type) {
            case BYTE:
                return this.byteBuffer.get(offset);
            case CHAR:
                return this.charBuffer.get(offset);
            case INT:
                return this.intBuffer.get(offset);
            default:
                throw new UnsupportedOperationException("Not reached");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Type getType() {
        return this.type;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int arrayOffset() {
        switch (this.type) {
            case BYTE:
                return this.byteBuffer.arrayOffset();
            case CHAR:
                return this.charBuffer.arrayOffset();
            case INT:
                return this.intBuffer.arrayOffset();
            default:
                throw new UnsupportedOperationException("Not reached");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] byteArray() {
        if ($assertionsDisabled || this.type == Type.BYTE) {
            return this.byteBuffer.array();
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public char[] charArray() {
        if ($assertionsDisabled || this.type == Type.CHAR) {
            return this.charBuffer.array();
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int[] intArray() {
        if ($assertionsDisabled || this.type == Type.INT) {
            return this.intBuffer.array();
        }
        throw new AssertionError();
    }

    public static Builder builder(int initialBufferSize) {
        return new Builder(initialBufferSize);
    }

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/antlr4-runtime-4.10.1.jar:org/antlr/v4/runtime/CodePointBuffer$Builder.class */
    public static class Builder {
        private Type type;
        private ByteBuffer byteBuffer;
        private CharBuffer charBuffer;
        private IntBuffer intBuffer;
        private int prevHighSurrogate;
        static final /* synthetic */ boolean $assertionsDisabled;

        static {
            $assertionsDisabled = !CodePointBuffer.class.desiredAssertionStatus();
        }

        private Builder(int initialBufferSize) {
            this.type = Type.BYTE;
            this.byteBuffer = ByteBuffer.allocate(initialBufferSize);
            this.charBuffer = null;
            this.intBuffer = null;
            this.prevHighSurrogate = -1;
        }

        Type getType() {
            return this.type;
        }

        ByteBuffer getByteBuffer() {
            return this.byteBuffer;
        }

        CharBuffer getCharBuffer() {
            return this.charBuffer;
        }

        IntBuffer getIntBuffer() {
            return this.intBuffer;
        }

        public CodePointBuffer build() {
            switch (this.type) {
                case BYTE:
                    this.byteBuffer.flip();
                    break;
                case CHAR:
                    this.charBuffer.flip();
                    break;
                case INT:
                    this.intBuffer.flip();
                    break;
            }
            return new CodePointBuffer(this.type, this.byteBuffer, this.charBuffer, this.intBuffer);
        }

        private static int roundUpToNextPowerOfTwo(int i) {
            int nextPowerOfTwo = 32 - Integer.numberOfLeadingZeros(i - 1);
            return (int) Math.pow(2.0d, nextPowerOfTwo);
        }

        public void ensureRemaining(int remainingNeeded) {
            switch (this.type) {
                case BYTE:
                    if (this.byteBuffer.remaining() < remainingNeeded) {
                        int newCapacity = roundUpToNextPowerOfTwo(this.byteBuffer.capacity() + remainingNeeded);
                        ByteBuffer newBuffer = ByteBuffer.allocate(newCapacity);
                        this.byteBuffer.flip();
                        newBuffer.put(this.byteBuffer);
                        this.byteBuffer = newBuffer;
                        return;
                    }
                    return;
                case CHAR:
                    if (this.charBuffer.remaining() < remainingNeeded) {
                        int newCapacity2 = roundUpToNextPowerOfTwo(this.charBuffer.capacity() + remainingNeeded);
                        CharBuffer newBuffer2 = CharBuffer.allocate(newCapacity2);
                        this.charBuffer.flip();
                        newBuffer2.put(this.charBuffer);
                        this.charBuffer = newBuffer2;
                        return;
                    }
                    return;
                case INT:
                    if (this.intBuffer.remaining() < remainingNeeded) {
                        int newCapacity3 = roundUpToNextPowerOfTwo(this.intBuffer.capacity() + remainingNeeded);
                        IntBuffer newBuffer3 = IntBuffer.allocate(newCapacity3);
                        this.intBuffer.flip();
                        newBuffer3.put(this.intBuffer);
                        this.intBuffer = newBuffer3;
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        public void append(CharBuffer utf16In) {
            ensureRemaining(utf16In.remaining());
            if (utf16In.hasArray()) {
                appendArray(utf16In);
                return;
            }
            throw new UnsupportedOperationException("TODO");
        }

        private void appendArray(CharBuffer utf16In) {
            if (!$assertionsDisabled && !utf16In.hasArray()) {
                throw new AssertionError();
            }
            switch (this.type) {
                case BYTE:
                    appendArrayByte(utf16In);
                    return;
                case CHAR:
                    appendArrayChar(utf16In);
                    return;
                case INT:
                    appendArrayInt(utf16In);
                    return;
                default:
                    return;
            }
        }

        private void appendArrayByte(CharBuffer utf16In) {
            if (!$assertionsDisabled && this.prevHighSurrogate != -1) {
                throw new AssertionError();
            }
            char[] in = utf16In.array();
            int inOffset = utf16In.arrayOffset() + utf16In.position();
            int inLimit = utf16In.arrayOffset() + utf16In.limit();
            byte[] outByte = this.byteBuffer.array();
            int outOffset = this.byteBuffer.arrayOffset() + this.byteBuffer.position();
            while (inOffset < inLimit) {
                char c = in[inOffset];
                if (c <= 255) {
                    outByte[outOffset] = (byte) (c & 255);
                    inOffset++;
                    outOffset++;
                } else {
                    utf16In.position(inOffset - utf16In.arrayOffset());
                    this.byteBuffer.position(outOffset - this.byteBuffer.arrayOffset());
                    if (!Character.isHighSurrogate(c)) {
                        byteToCharBuffer(utf16In.remaining());
                        appendArrayChar(utf16In);
                        return;
                    }
                    byteToIntBuffer(utf16In.remaining());
                    appendArrayInt(utf16In);
                    return;
                }
            }
            utf16In.position(inOffset - utf16In.arrayOffset());
            this.byteBuffer.position(outOffset - this.byteBuffer.arrayOffset());
        }

        private void appendArrayChar(CharBuffer utf16In) {
            if (!$assertionsDisabled && this.prevHighSurrogate != -1) {
                throw new AssertionError();
            }
            char[] in = utf16In.array();
            int inOffset = utf16In.arrayOffset() + utf16In.position();
            int inLimit = utf16In.arrayOffset() + utf16In.limit();
            char[] outChar = this.charBuffer.array();
            int outOffset = this.charBuffer.arrayOffset() + this.charBuffer.position();
            while (inOffset < inLimit) {
                char c = in[inOffset];
                if (!Character.isHighSurrogate(c)) {
                    outChar[outOffset] = c;
                    inOffset++;
                    outOffset++;
                } else {
                    utf16In.position(inOffset - utf16In.arrayOffset());
                    this.charBuffer.position(outOffset - this.charBuffer.arrayOffset());
                    charToIntBuffer(utf16In.remaining());
                    appendArrayInt(utf16In);
                    return;
                }
            }
            utf16In.position(inOffset - utf16In.arrayOffset());
            this.charBuffer.position(outOffset - this.charBuffer.arrayOffset());
        }

        private void appendArrayInt(CharBuffer utf16In) {
            char[] in = utf16In.array();
            int inOffset = utf16In.arrayOffset() + utf16In.position();
            int inLimit = utf16In.arrayOffset() + utf16In.limit();
            int[] outInt = this.intBuffer.array();
            int outOffset = this.intBuffer.arrayOffset() + this.intBuffer.position();
            while (inOffset < inLimit) {
                char c = in[inOffset];
                inOffset++;
                if (this.prevHighSurrogate != -1) {
                    if (Character.isLowSurrogate(c)) {
                        outInt[outOffset] = Character.toCodePoint((char) this.prevHighSurrogate, c);
                        outOffset++;
                        this.prevHighSurrogate = -1;
                    } else {
                        outInt[outOffset] = this.prevHighSurrogate;
                        outOffset++;
                        if (Character.isHighSurrogate(c)) {
                            this.prevHighSurrogate = c & 65535;
                        } else {
                            outInt[outOffset] = c & 65535;
                            outOffset++;
                            this.prevHighSurrogate = -1;
                        }
                    }
                } else if (Character.isHighSurrogate(c)) {
                    this.prevHighSurrogate = c & 65535;
                } else {
                    outInt[outOffset] = c & 65535;
                    outOffset++;
                }
            }
            if (this.prevHighSurrogate != -1) {
                outInt[outOffset] = this.prevHighSurrogate & 65535;
                outOffset++;
            }
            utf16In.position(inOffset - utf16In.arrayOffset());
            this.intBuffer.position(outOffset - this.intBuffer.arrayOffset());
        }

        private void byteToCharBuffer(int toAppend) {
            this.byteBuffer.flip();
            CharBuffer newBuffer = CharBuffer.allocate(Math.max(this.byteBuffer.remaining() + toAppend, this.byteBuffer.capacity() / 2));
            while (this.byteBuffer.hasRemaining()) {
                newBuffer.put((char) (this.byteBuffer.get() & 255));
            }
            this.type = Type.CHAR;
            this.byteBuffer = null;
            this.charBuffer = newBuffer;
        }

        private void byteToIntBuffer(int toAppend) {
            this.byteBuffer.flip();
            IntBuffer newBuffer = IntBuffer.allocate(Math.max(this.byteBuffer.remaining() + toAppend, this.byteBuffer.capacity() / 4));
            while (this.byteBuffer.hasRemaining()) {
                newBuffer.put(this.byteBuffer.get() & 255);
            }
            this.type = Type.INT;
            this.byteBuffer = null;
            this.intBuffer = newBuffer;
        }

        private void charToIntBuffer(int toAppend) {
            this.charBuffer.flip();
            IntBuffer newBuffer = IntBuffer.allocate(Math.max(this.charBuffer.remaining() + toAppend, this.charBuffer.capacity() / 2));
            while (this.charBuffer.hasRemaining()) {
                newBuffer.put(this.charBuffer.get() & 65535);
            }
            this.type = Type.INT;
            this.charBuffer = null;
            this.intBuffer = newBuffer;
        }
    }
}