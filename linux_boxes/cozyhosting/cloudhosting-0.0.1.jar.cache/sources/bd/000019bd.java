package net.bytebuddy.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.utility.nullability.MaybeNull;

@HashCodeAndEqualsPlugin.Enhance
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/byte-buddy-1.12.22.jar:net/bytebuddy/utility/StreamDrainer.class */
public class StreamDrainer {
    public static final int DEFAULT_BUFFER_SIZE = 1024;
    public static final StreamDrainer DEFAULT = new StreamDrainer();
    private static final int END_OF_STREAM = -1;
    private static final int FROM_BEGINNING = 0;
    private final int bufferSize;

    public boolean equals(@MaybeNull Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.bufferSize == ((StreamDrainer) obj).bufferSize;
    }

    public int hashCode() {
        return (getClass().hashCode() * 31) + this.bufferSize;
    }

    public StreamDrainer() {
        this(1024);
    }

    public StreamDrainer(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public byte[] drain(InputStream inputStream) throws IOException {
        int currentRead;
        List<byte[]> previousBytes = new ArrayList<>();
        byte[] currentArray = new byte[this.bufferSize];
        int currentIndex = 0;
        do {
            currentRead = inputStream.read(currentArray, currentIndex, this.bufferSize - currentIndex);
            currentIndex += Math.max(currentRead, 0);
            if (currentIndex == this.bufferSize) {
                previousBytes.add(currentArray);
                currentArray = new byte[this.bufferSize];
                currentIndex = 0;
            }
        } while (currentRead != -1);
        byte[] result = new byte[(previousBytes.size() * this.bufferSize) + currentIndex];
        int arrayIndex = 0;
        for (byte[] previousByte : previousBytes) {
            int i = arrayIndex;
            arrayIndex++;
            System.arraycopy(previousByte, 0, result, i * this.bufferSize, this.bufferSize);
        }
        System.arraycopy(currentArray, 0, result, arrayIndex * this.bufferSize, currentIndex);
        return result;
    }
}