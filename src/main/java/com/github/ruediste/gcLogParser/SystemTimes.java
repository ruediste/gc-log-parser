package com.github.ruediste.gcLogParser;

import java.time.Duration;

public class SystemTimes {

    public Duration userTime;
    public Duration sysTime;
    public Duration realTime;

    @Override
    public String toString() {
        return "SystemTimes [userTime=" + userTime + ", sysTime=" + sysTime
                + ", realTime=" + realTime + "]";
    }

}
