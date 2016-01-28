package com.github.ruediste.gcLogParser;

public class MemSize {
    private final long sizeInBytes;

    public MemSize(long sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
    }

    public long getSizeInBytes() {
        return sizeInBytes;
    }

    @Override
    public String toString() {
        return "MemSize [sizeInBytes=" + sizeInBytes + "]";
    }

}
