package com.github.ruediste.gcLogParser;

import java.time.Duration;

public class CmsParNewEvent extends CmsEvent {

    public long timeStamp;
    public GenerationSizeChange youngChange;
    public GenerationSizeChange totalChange;
    public Duration totalTime;
    public SystemTimes systemTimes;
    public Duration youngTime;

    @Override
    public String toString() {
        return "CmsParNewEvent [timeStamp=" + timeStamp + ", youngChange="
                + youngChange + ", totalChange=" + totalChange + ", totalTime="
                + totalTime + ", systemTimes=" + systemTimes + ", youngTime="
                + youngTime + "]";
    }

}
