package com.github.ruediste.gcLogParser;

import java.time.Duration;
import java.util.Collection;

import com.github.ruediste1.lambdaPegParser.DefaultParsingContext;

/**
 */
public class GcLogParser extends
        com.github.ruediste1.lambdaPegParser.Parser<DefaultParsingContext> {

    public GcLogParser(DefaultParsingContext ctx) {
        super(ctx);
    }

    public Collection<CmsEvent> cmsLog() {
        Collection<CmsEvent> result = ZeroOrMore(
                () -> FirstOf(() -> parNewEvent(), () -> {
                    String content = lineWithoutEOL();
                    String("\n");
                    return new CmsUnknownEvent(content);
                }));
        Optional(() -> new CmsUnknownEvent(lineWithoutEOL())).map(result::add);
        EOI();
        return result;
    }

    public java.lang.String lineWithoutEOL() {
        return ZeroOrMoreChars(ch -> ch != '\n', "any char");
    }

    public CmsParNewEvent parNewEvent() {
        CmsParNewEvent result = new CmsParNewEvent();
        result.timeStamp = timeStamp();
        String(": [GC ");
        timeStamp();
        String(": [ParNew: ");
        result.youngChange = generationSizeChange();
        String(",");
        optWhiteSpace();
        result.youngTime = time();
        String("]");
        optWhiteSpace();
        result.totalChange = generationSizeChange();
        String(",");
        optWhiteSpace();
        result.totalTime = time();
        String("]");
        optWhiteSpace();
        result.systemTimes = systemTimes();
        optWhiteSpace();
        Optional(() -> String("\n"));
        return result;

    }

    void optWhiteSpace() {
        ZeroOrMoreChars(Character::isWhitespace, "white space");
    }

    SystemTimes systemTimes() {
        SystemTimes result = new SystemTimes();
        String("[Times: user=");
        result.userTime = seconds();
        String(" sys=");
        result.sysTime = seconds();
        String(", real=");
        result.realTime = seconds();
        String(" secs]");
        return result;
    }

    public long intNumber(String expectation) {
        return Long.parseLong(OneOrMoreChars(Character::isDigit, expectation));
    }

    private Duration time() {
        Duration result = seconds();
        String(" secs");
        return result;
    }

    private Duration seconds() {
        String seconds = OneOrMoreChars(Character::isDigit, "seconds of time");
        String(".");
        String fraction = OneOrMoreChars(Character::isDigit,
                "fraction of time");
        return Duration.ofNanos(
                (long) (Double.parseDouble(seconds + "." + fraction) * 1.0e9));
    }

    public GenerationSizeChange generationSizeChange() {
        GenerationSizeChange result = new GenerationSizeChange();
        result.before = memSize();
        String("->");
        result.after = memSize();
        String("(");
        result.total = memSize();
        String(")");
        return result;
    }

    public MemSize memSize() {
        java.lang.String size = OneOrMoreChars(Character::isDigit,
                "memSizes of timestamp");
        String("K");
        return new MemSize(Long.parseLong(size) * 1024);
    }

    public long timeStamp() {
        String seconds = OneOrMoreChars(Character::isDigit,
                "seconds of timestamp");
        String(".");
        String milliseconds = OneOrMoreChars(Character::isDigit,
                "milliseconds of timestamp");
        return Long.parseLong(seconds + milliseconds);
    }
}