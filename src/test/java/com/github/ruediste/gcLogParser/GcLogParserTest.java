package com.github.ruediste.gcLogParser;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.github.ruediste1.lambdaPegParser.ParserFactory;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class GcLogParserTest {

    @Before
    public void before() {
    }

    @Test
    public void testParNewEvent() throws Exception {
        CmsParNewEvent event = parser(
                "560.358: [GC 560.358: [ParNew: 943744K->104832K(943744K), 0.2156700 secs] 3361575K->2569268K(47081088K), 0.2157850 secs] [Times: user=1.21 sys=0.04, real=0.22 secs]")
                        .parNewEvent();
        assertEquals(560358, event.timeStamp);
        assertEquals(2569268 * 1024L, event.totalChange.after.getSizeInBytes());
    }

    @Test
    public void parseLog() throws Exception {
        Collection<CmsEvent> log = parser(
                Files.toString(new File("cms.log"), Charsets.UTF_8)).cmsLog();
        log.stream().forEach(System.out::println);
    }

    GcLogParser parser(String input) {
        return ParserFactory.create(GcLogParser.class, input);
    }

}
