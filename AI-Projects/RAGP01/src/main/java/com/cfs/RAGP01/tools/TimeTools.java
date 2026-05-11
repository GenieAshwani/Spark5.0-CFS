package com.cfs.RAGP01.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class TimeTools {

    private static final Logger logger= LoggerFactory.getLogger(TimeTools.class);


    @Tool(name = "getCurrentTime",description = "get the current time")
    public String getCurrentTime()
    {
        logger.info("Getting current Time...");
        return java.time.LocalTime.now().toString();
    }

    @Tool(name = "getCurrentTimeWithZone",description = "get the current time by zone")
    public String getCurrentTimeWithZone(@ToolParam(description = "value representing time zone") String zone)
    {
        logger.info("Getting time by zone....");
        return java.time.ZonedDateTime.now(java.time.ZoneId.of(zone)).toString();
    }
}
