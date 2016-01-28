package com.github.ruediste.gcLogParser;

public class GenerationSizeChange {

    public MemSize before;
    public MemSize after;
    public MemSize total;

    @Override
    public String toString() {
        return "GenerationSizeChange [before=" + before + ", after=" + after
                + ", total=" + total + "]";
    }
}
