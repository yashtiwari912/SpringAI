package com.yashdev.Tools.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;

import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class GetDateAndTime {

    //Information Tool
    @Tool(description = "Get Current Date and Time")
    public String getCurrentDateAndTime(){
        log.info("Get Current Date and Time Tool Called");
        return LocalDateTime
                .now()
                .atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
    }

    //Action Tool
    @Tool(description = "Set Alarm for given time")
    void setAlarm(@ToolParam(description = "Time in ISO-8601 format") String time){
        var dateTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        log.info("Set the alarm for given time {}",dateTime );
    }
}
