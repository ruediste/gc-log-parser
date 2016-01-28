package com.github.ruediste.gcLogParser;

public class CmsUnknownEvent extends CmsEvent {

    public String content;

    public CmsUnknownEvent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CmsUnknownEvent [content=" + content + "]";
    }

}
