package org.HdrHistogram.packedarray;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/HdrHistogram-2.1.12.jar:org/HdrHistogram/packedarray/ResizeException.class */
class ResizeException extends Exception {
    private int newSize;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResizeException(int newSize) {
        this.newSize = newSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getNewSize() {
        return this.newSize;
    }
}